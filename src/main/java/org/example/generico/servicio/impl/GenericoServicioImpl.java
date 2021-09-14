package org.example.generico.servicio.impl;


import lombok.RequiredArgsConstructor;
import org.example.generico.dto.GenericoDto;
import org.example.generico.entidad.GenericoEntidad;
import org.example.generico.mapper.GenericoMapper;
import org.example.generico.servicio.GenericoServicio;
import org.example.generico.servicio.ValidacionServicio;
import org.example.generico.validaciones.TipoValidacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
public abstract class GenericoServicioImpl<ENTIDAD extends GenericoEntidad<ID>,
        DTO extends GenericoDto<ID>,
        ID> implements GenericoServicio<DTO, ID> {

    @Autowired(required = false)
    @Lazy
    private ValidacionServicio validacionServicio;

    @Override
    public DTO guardar(DTO dto) {
        validacionServicio.validarClase(dto, TipoValidacion.CREAR);
        ENTIDAD entidad = getMapper().BToA(dto);
        entidad = getDao().save(entidad);
        return getMapper().AToB(entidad);
    }

    @Override
    public DTO actualizar(DTO dto) {
        validacionServicio.validarClase(dto, TipoValidacion.ACTUALIZAR);
        ENTIDAD entidad = getMapper().BToA(dto);
        entidad = getDao().save(entidad);
        return getMapper().AToB(entidad);
    }

    @Override
    public void eliminar(ID id) {
        getDao()
                .findById(id)
                .map(entidad -> getMapper().AToB(entidad))
                .ifPresent(dto ->
                        validacionServicio.validarClase(dto, TipoValidacion.ELIMINAR)
                );
        getDao().deleteById(id);
    }

    @Override
    public Optional<DTO> buscarPorId(ID id) {
        return getDao()
                .findById(id)
                .map(entidad -> getMapper().AToB(entidad));
    }

    @Override
    public Stream<DTO> buscarTodo() {
        return StreamSupport
                .stream(
                        getDao().findAll().spliterator(),
                        false
                )
                .map(entidad -> getMapper().AToB(entidad));
    }

    protected abstract CrudRepository<ENTIDAD, ID> getDao();

    protected abstract GenericoMapper<ENTIDAD, DTO> getMapper();
}
