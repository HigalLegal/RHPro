# RHPro
A aplicação é um sistema de gestão de Recursos Humanos projetado para automatizar e otimizar as atividades relacionadas à administração de pessoal.

## 💻 Visão Geral
RHPro é destinada a funcionários do RH, com finalidade de fornecer uma plataforma centralizada para gerenciar tarefas rotineiras. Com um enfoque na eficiência operaciona e redução de custos, a aplicação oferece uma série de funcionalidades voltadas para o acompanhamento e controle de funcionários. Desta forma, RHpro conseguirá melhorar a gestão de recursos humanos em uma organização, tornando o processo mais eficiente, preciso e orientado para o cumprimento dos objetivos estratégicos da empresa

### 💿 Tecnologias
![Tecnologias](https://skillicons.dev/icons?i=java,maven,postgres)

## 📊 Diagramas

<details>
<summary><strong>Diagrama de classe</strong></summary>
  
``` mermaid
classDiagram
    FolhaDePagamentoController <|-- FolhaDePagamento
    FuncionarioController <|-- Funcionario
    LicencaController <|-- Licenca
    PontoController <|-- Ponto
    Funcionario "1" <|-- FolhaDePagamento
    Funcionario "1" <|-- Licenca
    Ponto <|--  Funcionario
    
    class FolhaDePagamentoController{
        +listarTodos() List<FuncionarioOutputAll>
        +retornarPorId(Long id) FuncionarioOutputOne
        +criar(FuncionarioInput funcionarioInput) void
        +atualizar(Long id, FuncionarioInput funcionarioInput) void
        +deletar(Long id) void
    }

    class FuncionarioController {
        +listarTodos() List<FuncionarioOutputAll>
        +retornarPorId(Long id) FuncionarioOutputOne
        +criar(FuncionarioInput funcionarioInput) void
        +atualizar(Long id, FuncionarioInput funcionarioInput) void
        +deletar(Long id) void
    }

    class LicencaController {
        +listarTodos() List<LicencaOutput>
        +retornarPorId(Long id) LicencaOutput
        +criar(LicencaInput licencaInput) void
        +atualizar(Long id, LicencaInput licencaInput) void
        +deletar(Long id) void
    }

    class PontoController{
        -id: Long
        -horaChegada: LocalDateTime
        -horaSaida: LocalDateTime
        -funcionario: Funcionario
        +calcularHoraTrabalhadaPorDia() BigDecimal
    }
    
    class Licenca{
        -id: Long
        -data: LocalDate
        -funcionario: Funcionario
    }

    class FolhaDePagamento{
        -id: Long
        -porcentagemIRF: BigDecimal
        -funcionario: Funcionario
        +calcularIRF() BigDecimal 
        +calcularSalarioLiquido() BigDecimal 
    }

    class Funcionario{
        -id: Long
        -nome: String
        -sobrenome: String
        -emailCorporativo: String
        -CPF: String
        -dataDeNascimento: LocalDate
        -salarioHora: BigDecimal
        -folhaDePagamento: FolhaDePagamento
    }

    class Ponto{
        -id: Long
        -horaChegada: LocalDateTime
        -horaSaida: LocalDateTime
        -funcionario: Funcionario
        +calcularHoraTrabalhadaPorDia() BigDecimal
    }

    class Usuario{
        -id: Long
        -email: String
        -senha: String
        -admin: Boolean
        -funcionario: Funcionario
    }
```

</details>

<details>
<summary><strong>Diagrama lógico do banco de dados</strong></summary>
  
``` mermaid
erDiagram
    Licenca ||--o{ Funcionario: ""
    FolhaDePagamento ||--o{ Funcionario: ""
    Usuario ||--o{ Funcionario: ""
    Ponto ||--o{ Funcionario: ""

    Funcionario {
        PrimaryKey id
        varchar(255) nome
        varchar(255) sobrenome
        varchar(255) emailCorporativo
        varchar(11) CPF
        date dataDeNascimento
        double salarioHora
    }
    FolhaDePagamento {
        PrimaryKey id
        ForeignKey funcionarioId
        date dataFolha
    }
    
    Licenca {
        PrimaryKey id
        ForeignKey funcionarioId
        date dataFolha
        bytea atestado
    }

    Usuario {
        PrimaryKey id
        ForeignKey funcionarioId
        varchar(255) email
        varchar(255) senha
        boolean admin
    }

    Ponto {
        PrimaryKey id
        ForeignKey funcionarioId
        timestamp horaChehada
        timestamp horaSaida
    }
```

</details>

## ⏬ Instalação
    
Para rodar este projeto localmente, siga os seguintes passos:

<details>
<summary><strong>Setup inicial e requisitos</strong></summary>
  
1. Instale o [Java](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html), versão 21
2. Instale o [Maven](https://dlcdn.apache.org/maven/maven-3/3.9.5/), versão 3.9.5
3. Instale o [PostgreSQL](https://www.postgresql.org/download/)
4. Crie um banco de dados chamado "rhproDB" na porta 5432 

</details>

<details>
<summary><strong>Instalação e execução</strong></summary>
  
1. Clone o repositório:
```bash
git clone <repository-url>
```

2. Navegue até o diretório do projeto:
```bash
cd <project-directory>
```

3. Execute o projeto com:
```bash
mvn spring-boot:run
```

</details>

## 👨‍💻 Desenvolvedores
Este projeto é um esforço colaborativo dos seguintes desenvolvedores:

<table>
  <tr>
    <td>
      <a href="https://github.com/HigalLegal" target="_blank">
        <img src="https://avatars.githubusercontent.com/u/89707397?v=4" width=100 />
        <p align="center">Higor<br/> Morais </p>
      </a>
    </td>
    <td>
      <a href="https://github.com/IltonWhatever" target="_blank">
        <img src="https://avatars.githubusercontent.com/u/101358506?v=4" width=100 />
        <p align="center">José<br/> Ilton </p>
      </a>
    </td>
    <td>
      <a href="https://github.com/alefmoreira" target="_blank">
        <img src="https://avatars.githubusercontent.com/u/79259745?v=4" width=100 />
        <p align="center">Alef<br/> Moreira </p>
      </a>
    </td>
    <td>
      <a href="https://github.com/felipecomarques" target="_blank">
        <img src="https://avatars.githubusercontent.com/u/57302703?v=4" width=100 />
        <p align="center">Felipe<br/> Marques </p>
      </a>
    </td>
    <td>
      <a href="https://github.com/geraldo-werberty" target="_blank">
        <img src="https://avatars.githubusercontent.com/u/162387824?v=4" width=100 />
        <p align="center">Geraldo <br/> Werberty </p>
      </a>
    </td>
    <td>
      <a href="https://github.com/Jhon-Wesley7" target="_blank">
        <img src="https://avatars.githubusercontent.com/u/128758538?v=4" width=100 />
        <p align="center">Jhon <br/> Wesley </p>
      </a>
    </td>
    
  </tr>
</table>


## ⚖️ Licença
Código liberado sob a [LICENÇA]().
