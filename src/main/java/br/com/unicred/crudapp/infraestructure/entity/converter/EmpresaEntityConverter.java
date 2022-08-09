package br.com.unicred.crudapp.infraestructure.entity.converter;

import br.com.unicred.crudapp.domain.model.empresa.Empresa;
import br.com.unicred.crudapp.infraestructure.entity.EmpresaEntity;

import java.util.List;

public interface EmpresaEntityConverter {

    EmpresaEntity converterParaEntity(Empresa empresa);

    Empresa converterParaEmpresa(EmpresaEntity empresaEntity);

    List<Empresa> converterParaListaEmpresa(List<EmpresaEntity> empresasEntity);
}
