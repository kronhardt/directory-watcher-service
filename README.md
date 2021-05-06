# Directory Watcher

Aplicação que faz a leitura de arquivos de um diretório, realiza uma análise dos dados do arquivo e produz um 
relatório sobre os dados analisados. Também é realizada a inscrição no diretório e qualquer evento de entrada é lido.


## Começando
Para executar o projeto, será necessário instalar os seguintes programas:

- [JDK 11: Necessário para rodar o projeto Java](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)
- [Gradle 7: Necessário para executar o projeto junto com as dependências.](https://gradle.org/install/)

## Desenvolvimento
Para iniciar o desenvolvimento é necessário clonar o projeto do GitHub.

> git clone https://github.com/kronhardt/directory-watcher-service.git

## Construção(build)
Para construir o projeto gradle é necessário executar o comando abaixo:

> gradle build

O comando irá baixar todas as dependências do projeto. Também serão executados todos os testes unitários,
se algum falhar, o Gradle exibirá essa informação no console.

## Publicação(deploy)
Para 'startar' o projeto é necessário executar o comando abaixo:

> gradle bootRun

## Configuração
A aplicação irá ler os arquivos do diretório configurado na propriedade 'directory.entrance' do application.yml
e exportar os dados analisados no diretório configurado na propriedade 'directory.output'.


## Formatação de Arquivo

Só será processado arquivos com a extensão **.dat**

## Formatação de linha

Um arquivo contem linhas que podem ser formatadas em 3 tipos de informações:

**Vendedor**

Os dados do vendedor têm o formato id 001 e a linha terá o seguinte formato:
 > 001çCPFçNameçSalary

**Cliente**
Os dados do cliente têm o formato id 002 e a linha terá o seguinte formato:

 > 002çCNPJçNameçBusiness Area

**Venda**

Os dados de vendas têm o formato id 003. Dentro da linha de vendas, existe a lista
de itens, que é envolto por colchetes []. A linha terá o seguinte formato:
> 003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name


Exemplo de Arquivo:

> 001ç1234567891234çPedroç50000 <br>
> 001ç3245678865434çPauloç40000.99 <br>
> 002ç2345675434544345çJose da SilvaçRural <br>
> 002ç2345675433444345çEduardo PereiraçRural <br>
> 003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro <br>
> 003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo <br>

## Resultados

O conteúdo do arquivo de saída deve resumir os seguintes dados:


- Quantidade de vendedor no arquivo de entrada
- Quantidade de clientes no arquivo de entrada
- ID da venda mais cara
- O pior vendedor


Exemplo de saída gerada:

```
 Resultados para /home/gregorykronhardt/data/out/teste.done.dat - 06 mai 2021 01:27:45
 -------------------------------------------------------------------------------------------
 Quantidade de clientes: 2
 Quantidade de vendedores: 2
 ID da venda mais cara: 10
 O pior vendedor: Paulo
```
