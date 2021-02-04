package com.ecommerce.j3.service;

public interface ServiceCrudInterface<Req, Res> {

    public Res save(Req request);

    public Res update(Req request);

    public Res findOne(Long id);

//    public BodyData<List<Res>> findAll();

    public void remove(Long id);
}
