# agenda-app

## Decisões técnicas

### Por que Flyway em vez de deixar o Hibernate criar as tabelas?

O Hibernate não entende renomeações: se um campo da entidade muda de nome, ele cria uma nova coluna vazia e os dados continuam na coluna antiga. Com o Flyway, cada mudança no banco é um script SQL versionado no Git, e o histórico de migrations aplicadas garante que qualquer ambiente (o meu, o de outro desenvolvedor ou produção) chegue exatamente ao mesmo estado do banco.

### Por que a entidade não tem setters?

No lugar dos setters, a entidade tem métodos de negócio, como `desativar()`. Assim, toda alteração passa por um único ponto, e as regras não podem ser contornadas: nenhuma parte do sistema consegue, por exemplo, deixar um serviço com preço negativo. Além disso, `servico.desativar()` deixa claro o que aconteceu no negócio, enquanto `setAtivo(false)` só mostra que um campo mudou.

### Por que a API devolve DTOs em vez da entidade?

Primeiro, para não expor dados internos: o campo `ativo` do serviço, por exemplo, não aparece na listagem. Segundo, para desacoplar o contrato da API do banco: se o front precisar que o campo `preco` se chame `valor` no JSON, altero apenas o DTO, sem mexer na entidade nem no banco.

### Por que injeção de dependências pelo construtor em vez de `@Autowired` no campo?

Pelo construtor, o campo pode ser `final`: o compilador obriga a preenchê-lo, então o objeto nunca existe com a dependência vazia, e ela não pode ser trocada depois. Também facilita os testes: posso criar o service com `new`, passando um repositório falso, sem subir o Spring. Com `@Autowired` em um campo privado, não haveria como entregar esse repositório falso de forma simples.

### Por que separar o código em controller, service e repository?

Cada camada tem uma responsabilidade única. O controller cuida apenas do HTTP; o service contém a lógica do caso de uso e converte entidades em DTOs; o repository acessa o banco. Assim, sei exatamente onde alterar o código ou procurar um erro: se o caminho de uma URL mudar, altero só o controller, e se a forma de acessar o banco mudar, altero só o repository.
