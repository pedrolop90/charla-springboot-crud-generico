package org.example.generico.mapper;

import java.util.stream.Stream;

public interface GenericoMapper<A, B> {

    B AToB(A a);

    A BToA(B b);

    Stream<B> AToB(Stream<A> a);

    Stream<A> BToA(Stream<B> b);

}
