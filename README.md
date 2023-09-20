# Serviço envio de email

Objetivo deste projeto é criar um serviço de envio de email desacoplada de serviços de envio de email utilizando:
* Arquitetura Limpa
* Java 
* Spring Boot
* Implementação concreta da SendGrid

### Informações Úteis

Para o usuário que irá usar a SendGrid como serviço de envio de email, basta ir em application.properties e inserir a
chave da api fornecida pela SendGrid em 
~~~ 
app.sendgrid.api-key=<<your-sendgrid-api-key>>
~~~
Cabe aqui informar que é interessante colocar esta chave como variável de ambiente 
e referenciar a variável de ambiente no application.propriedade.
Para informar qual será o endereço de email que enviará os emails, basta editar a propriedade
~~~ 
app.mail.from=<<your-email>>
~~~
Com isso sua aplicação estará pronta para rodar e pode-se realizar o deploy onde for necessário. Para testar,
basta rodar aplicação. Ela estará rodando em http://localhost:8080 e o endpoint estará disponível para enviar email:

|Endpoint|          |
|----|---------:|
|POST| /api/email |

#### **request**
~~~json
{
  "to":"<<endereço de email>>",
  "subject":"<<Assunto do email>>",
  "body": "<<Corpo do email>>"
}
~~~

#### **response**
Status Code - 200
~~~
Email sent successfully
~~~
#### **response error**
Status Code - 500
~~~
Error while sending email
~~~

### Implementando um novo Serviço de Email

A Arquitetura limpa neste projeto nos possibilitou um desacoblamento com a SendGrid, pois nossa aplicação não depende
diretamente da SendGrid para funcionar, de modo que para  utilizar outro serviço de email, por exemplo SES, basta seguir
os passos:
* Adicionar as dependências e configurações do novo serviço de email
* Criar um package dentro de infrastructure contendo nome do serviço de email
* Criar um serviço com nome  <<nome-do-serviço>>EmailSender que implementa a interface EmailSenderGateway
* Criar um qualifier para a nova implementação e modificar a injeção de dependência EmailSenderUseCaseImpl para usar a 
nova implementação.
