## Criar uma aplicação CRUD

### TODO

Criar aplicação crud expondo:

- [ ] empresas
- [ ] setores
- [ ] funcionários

Cadastros devem ser feitos expondo apis REST

Utilizar a estrutura de desenvolvimento baseada em DDD

* Cadastro de empresa

Campos que devem ser cadastrados: id, nome, cnpj, endereço, numero, cidade, estados, bairro, número, cep, email e telefone

Regras de negócio: os campos não podem ser nulo ou estar em branco, caso não sejão lançados lançar uma exceção que deve ser tratada através de um HandlerException do spring. O cnpj precisa ser um cnpj válido, caso não sejão lançados lançar uma exceção que deve ser tratada através de um HandlerException do spring.
O cep precisa ser um cep válido e existente, para verificar se um cep existe basta consultar o endpoint https://viacep.com.br/ws/NUMERO_CEP, ec:
https://viacep.com.br/ws/98995000, caso não sejão lançados lançar uma exceção que deve ser tratada através de um HandlerException do spring.
obs: o client para a consulta do cep deve ser usado o openfeign
Validar se o email segue um padrão válido, lançar exceção tratada pelo HandlerException do spring em caso email inválido. Validar se o telefone informado é valido, no caso ele deve ter 11 dígitos, somente dígitos, lançar exceção tratada pelo HandlerException do spring em caso telefone inválido.

Endpoints exposto: criar, ler, atualizar, excluir e listar

* Cadastro de setores

Campos que devem ser cadastrados: id, id_empresa, nome, descrição

Regras de negócio: os campos não podem ser nulo ou estar em branco, caso não sejão lançados lançar uma exceção que deve ser tratada através de um HandlerException do spring

endpoints exposto: criar, ler, atualizar, excluir e listar

* Cadastro de funcionários

Campos que devem ser cadastrados: id, id_setor, nome, cpf

Regras de negócio: os campos não podem ser nulo ou estar em branco, caso não sejão lançados lançar uma exceção que deve ser tratada através de um HandlerException do spring.
O cpf precisa ser um cpf válido, caso não sejão lançados lançar uma exceção que deve ser tratada através de um HandlerException do spring

endpoints exposto: criar, ler, atualizar, excluir e listar, listar todos os funcionários de um determinado setor

obs: Salvar todos as chamadas ao micro serviço em uma coleção do postman

* Testes:

Todas as regras de negócio devem ser tratadas com o uso do JUnit, para testar a integração com o serviço que analisa um cep existente o mesmo deve ser feito através da técnologia wiremock

- [ ] empresas
- [ ] setores
- [ ] funcionários
