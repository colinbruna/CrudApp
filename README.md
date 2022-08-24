## Criar uma aplicação CRUD

### TODO

Criar aplicação crud expondo:

- [x] empresas
- [x] setores
- [x] funcionários

Cadastros devem ser feitos expondo apis REST

Utilizar a estrutura de desenvolvimento baseada em DDD

* Cadastro de empresa

Campos que devem ser cadastrados: id, nome, cnpj, endereço, numero, cidade, estados, bairro, número, cep, email e telefone

Regras de negócio: os campos não podem ser nulo ou estar em branco, caso não sejão lançados lançar uma exceção que deve ser tratada através de um HandlerException do spring. O cnpj precisa ser um cnpj válido, caso não sejão lançados lançar uma exceção que deve ser tratada através de um HandlerException do spring.
O cep precisa ser válido e existente, para verificar se um cep existe basta consultar o endpoint https://viacep.com.br/ws/NUMERO_CEP, ec:
https://viacep.com.br/ws/98995000, caso não sejão lançados lançar uma exceção que deve ser tratada através de um HandlerException do spring.
Obs.: o client para a consulta do cep deve ser usado o openfeign

Validar se o e-mail segue um padrão válido, lançar exceção tratada pelo HandlerException do spring em caso email inválido. Validar se o telefone informado é valido, no caso ele deve ter 11 dígitos, somente dígitos, lançar exceção tratada pelo HandlerException do spring em caso telefone inválido.

Endpoints exposto: criar, ler, atualizar, excluir e listar

* Cadastro de setores

Campos que devem ser cadastrados: id, id_empresa, nome, descrição

Regras de negócio: os campos não podem ser nulo ou estar em branco, caso não sejão lançados lançar uma exceção que deve ser tratada através de um HandlerException do spring

endpoints exposto: criar, ler, atualizar, excluir e listar

* Cadastro de funcionários

Campos que devem ser cadastrados: id, id_setor, nome, cpf

Regras de negócio: os campos não podem ser nulo ou estar em branco, caso não sejão lançados lançar uma exceção que deve ser tratada através de um HandlerException do spring.
O cpf precisa ser um cpf válido, caso não sejão lançados lançar uma exceção que deve ser tratada através de um HandlerException do spring

Endpoints exposto: criar, ler, atualizar, excluir e listar, listar todos os funcionários de um determinado setor

Obs.: Salvar todas as chamadas ao microsserviço numa coleção do postman

* Testes:

Todas as regras de negócio devem ser tratadas com o uso do JUnit, para testar a integração com o serviço que analisa um cep existente o mesmo deve ser feito através da técnologia wiremock

- [ ] empresas

### DDD
“É um conjunto de princípios com foco em domínio, exploração de modelos de formas criativas e definir e falar a linguagem Ubíqua, baseado no contexto delimitado.”
Domínio é o coração do negócio em que você está trabalhando. É baseado num conjunto de ideias, conhecimento e processos de negócio. É a razão do negócio existir. Sem o domínio todo o sistema, todos os processos auxiliares, não servirão para nada.

### REST
REST(Representational State Transfer, que significa Transferência Representacional de Estado) é um modelo de arquitetura e não uma linguagem ou tecnologia de programação, que fornece diretrizes para que os sistemas distribuídos se comuniquem diretamente usando os princípios e protocolos existentes da Web.

### Exceptions
A exceção é um evento não esperado que ocorre no sistema quando está em tempo de execução (Runtime). Geralmente quando o sistema captura alguma exceção o fluxo do código fica interrompido.
Para conseguir capturar uma exceção, é preciso fazer antes o tratamento. O uso dos tratamentos é importante nos sistemas porque auxilia em falhas como: comunicação, leitura e escrita de arquivos, entrada de dados inválidos, acesso a elementos fora de índice, entre outros.

### ModelMapper
Os aplicativos geralmente consistem em modelos de objetos semelhantes, mas diferentes, em que os dados em dois modelos podem ser semelhantes, mas a estrutura e os interesses dos modelos são diferentes. O mapeamento de objetos facilita a conversão de um modelo para outro, permitindo que modelos separados permaneçam segregados. O objetivo do ModelMapper é facilitar o mapeamento de objetos, determinando automaticamente como um modelo de objeto é mapeado para outro, com base em convenções. 

### @ControllerAdvice e @ExceptionHandler
@ExceptionHandler: essa anotação é responsável por definir um tratamento para exceções específicas, nesse projeto essa anotação é centralizada dentro de uma única classe anotada com @ControllerAdvice. A anotação @ControllerAdvice é uma especialização de @Component que permite definir alguns métodos comuns para vários controllers. Aqui, será definir um tratamento comum de exceção.

### Validação CEP

Spring Cloud OpenFeign: cliente REST declarativo para aplicativos Spring Boot. O feign facilita a escrita de clientes de serviço da Web com suporte a anotações conectávies. Vantagem sobre o uso do feign é que não precisamos escrever nenhum código para chamar o serviço, a não ser uma definição de interface.

- Add as dependências gradle: 

implementation 'org.springframework.cloud:spring-cloud-starter-openfeign:3.1.3'

mavenBom 'org.springframework.cloud:spring-cloud-dependencies:2021.0.3'

- Adicionar @EnableFeignClients à classe principal. Com essa anotação, habilitamos a verificação de componentes para interfaces que declaram que são clientes do Feign.
- Criada Interface ViaCepClient
- Criado método para validar o cep na serviceImpl e criada classe ViaCepResponse.

### Expressões regulares - regex
Uma expressão regular é uma sequência de caracteres que especifica um padrão de pesquisa em text. Normalmente, esses padrões são usados por algoritmos de busca de strings para operações de "localizar" ou "localizar e substituir" em strings ou para validação de entrada. A API Java fornece duas classes específicas para tal tarefa, no pacote java.util.regex, sendo elas: java.util.regex.Pattern e java.util.regex.Matcher.

### Operador ternário
Estrutura: condição? valor se for verdadeiro : valor se for falso

### Códigos de status de respostas HTTP
Respostas de informação (100-199)

Respostas de sucesso (200-299)

201- Created: a requisição foi bem sucedida e um novo recurso foi criado como resultado.

204- No Content: não há conteúdo para enviar para esta solicitação.

Redirecionamentos (300-399)

Erros do cliente (400-499)

400- bad request: significa que o servidor não entendeu a requisição, pois está com uma sintaxe inválida.

404- not found: o servidor não pode encontrar o recurso solicitado.

Erros do servidor (500-599)

### Debug

### Optional
A finalidade da classe é fornecer uma solução de nível de tipo para representar valores opcionais em vez de referências nulas.

### Anotações

@Autowired: anotação para indicar que os parametros do construtor serão injetados

@PathVariable: é utilizado quando o valor da variável é passada diretamente na URI como um valor que faz parte da URI

### ResponseEntity
Representa toda a resposta HTTP: código de status, cabeçalhos e corpo.

