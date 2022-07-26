package br.com.unicred.crudapp.infraestructure.adapter.empresa;

import br.com.unicred.crudapp.domain.model.empresa.Empresa;
import br.com.unicred.crudapp.domain.service.empresa.EmpresaAdapter;
import br.com.unicred.crudapp.infraestructure.adapter.empresa.converter.EmpresaEntityConverter;
import br.com.unicred.crudapp.infraestructure.entity.empresa.EmpresaEntity;
import br.com.unicred.crudapp.infraestructure.repository.empresa.EmpresaRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaAdapterGatewayImpl implements EmpresaAdapter {

    private final EmpresaRepository repository;
    private final EmpresaEntityConverter converter;

    @Autowired
    public EmpresaAdapterGatewayImpl(final EmpresaRepository repository, final EmpresaEntityConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public Empresa salvar(final Empresa empresa) {
        EmpresaEntity empresaEntity = converter.converterParaEntity(empresa);
        EmpresaEntity empresaEntitySalva = repository.save(empresaEntity);
        return converter.converterParaEmpresa(empresaEntitySalva);
    }

    public Empresa alterar(final String id, final Empresa empresa) {
        Optional<EmpresaEntity> optEmpresaEntity = repository.findById(new ObjectId(id));
        //opt recebe a empresa do banco

        if (optEmpresaEntity.isPresent()) {
            //empresa encontrada fazer as alterações
            EmpresaEntity entity = converter.converterParaEntity(empresa);
            entity.setId(optEmpresaEntity.get().getId());
            //a empresa com os dados novos precisa receber o ID vinculado no mongo, se não fizer assim,
            // não salva a alteração
            return converter.converterParaEmpresa(repository.save(entity));
        }

        return null; //se não encontrar o id, retorna null e trata na controller
    }

    public void excluir(final String id) {
        repository.deleteById(new ObjectId(id));
    }

    public Empresa buscar(final String id) {
        Optional<EmpresaEntity> optEmpresaEntity = repository.findById(new ObjectId(id));
        //Optional: é usado basicamente como checagem se um objeto está presente ou não na aplicação.
        // Vai evitar as exceções caso retorne dados nulos.

        //converter.converterParaEmpresa(optEmpresaEntity.get());

        return optEmpresaEntity.map(converter::converterParaEmpresa).orElse(null);
        //Se um valor estiver presente, retornará o resultado da aplicação da função de mapeamento,
        // caso contrário, retornará um opcional vazio
    }

    public List<Empresa> listar() {
        List<EmpresaEntity> empresasEntity = repository.findAll();
        //lista empresasEntity recebe todas empresas encontradas no repositório
        return converter.converterParaListaEmpresas(empresasEntity);
        //converte para lista empresas
    }
}
