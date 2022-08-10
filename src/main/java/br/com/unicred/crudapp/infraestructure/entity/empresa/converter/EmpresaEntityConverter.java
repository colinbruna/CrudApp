package br.com.unicred.crudapp.infraestructure.entity.empresa.converter;

import br.com.unicred.crudapp.domain.model.empresa.Empresa;
import br.com.unicred.crudapp.infraestructure.entity.empresa.EmpresaEntity;

import java.util.List;

public interface EmpresaEntityConverter {

    EmpresaEntity converterParaEntity(Empresa empresa);

    Empresa converterParaEmpresa(EmpresaEntity empresaEntity);

    List<Empresa> converterParaListaEmpresas(List<EmpresaEntity> empresasEntity);
}
