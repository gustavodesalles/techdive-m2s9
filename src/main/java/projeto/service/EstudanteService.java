package projeto.service;

import projeto.business.EstudanteBusiness;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EstudanteService {

    @Inject
    private EstudanteBusiness estudanteBusiness;

}
