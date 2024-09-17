# LDS2 - Sistema de Aluguel de Carros

Este repositório contém o trabalho em grupo realizado para a disciplina **Laboratório de Desenvolvimento de Software II**, do 4º período do curso de Engenharia de Software da PUC Minas.

## 📽 Apresentação

- [Vídeo de apresentação do projeto](https://youtu.be/1Q6J9Q6Z9ZQ)

## 👥 Integrantes da equipe

- [Caio Costa Souza]()
- [Gustavo Pereira de Oliveira]()
- [Luís Felipe Teixeira Dias Brescia](https://luisbrescia.tech)
- [Victor Reis Carlota](https://victor-room.vercel.app)

## 👨‍🏫 Professor responsável

- João Paulo Carneiro Aramuni

## 🛠 Rodar localmente

### 📋 Pré-requisitos

Certifique-se de ter os seguintes programas instalados:

- **[Node.js](https://nodejs.org/en/)** (para executar a aplicação Vue.js)
- **[Docker](https://www.docker.com/)** (para executar o Spring Boot e o PostgreSQL sem instalar Java localmente)

### 🚀 Passo a Passo

### 1️⃣ **Clonar o Repositório**

Primeiramente, clone este repositório em sua máquina local:

```bash
git clone https://github.com/seu-usuario/aracar.git
```

### 2️⃣ **Configurar o Backend (Spring Boot)**

#### **Caso você tenha Java e Maven instalados:**

1. Navegue até a pasta `backend`:

   ```bash
   cd aracar/backend
   ```

2. Execute o comando Maven para rodar o projeto:

   ```bash
   mvn spring-boot:run
   ```

   > A aplicação estará disponível em `http://localhost:8080`

#### **Caso você NÃO tenha Java e Maven instalados (usando apenas Docker):**

1. **Criar um Alias para o Maven Dockerizado**

   Crie um alias em seu terminal para usar o Maven dentro de um contêiner Docker:

   ```bash
   alias mvn='docker run --rm -it \
     -p 8080:8080 \
     -e HOME=/app \
     -v "$PWD":/app \
     -v "$HOME/.m2":/app/.m2 \
     -w /app \
     maven:3.8.8-openjdk-17-slim \
     mvn -Dmaven.repo.local=/app/.m2/repository'
   ```

   **Explicação de cada parâmetro:**

   - `--rm`: Remove o contêiner após a execução.
   - `-it`: Modo interativo com terminal.
   - `-p 8080:8080`: Mapeia a porta 8080 do contêiner para a porta 8080 do host.
   - `-e HOME=/app`: Define a variável de ambiente `HOME` para `/app`.
   - `-v "$PWD":/app`: Monta o diretório atual no contêiner.
   - `-v "$HOME/.m2":/app/.m2`: Monta o repositório Maven local para cache de dependências.
   - `-w /app`: Define o diretório de trabalho dentro do contêiner.
   - `maven:3.8.8-openjdk-17-slim`: Usa a imagem Maven com Java 17.
   - `-Dmaven.repo.local=/app/.m2/repository`: Define o repositório local do Maven.

2. **Carregar o Alias no Terminal**

   Após criar o alias, carregue-o no terminal atual:

   ```bash
   source ~/.bashrc   # ou source ~/.zshrc, dependendo do seu shell
   ```

3. **Navegar até a pasta `backend`**

   ```bash
   cd aracar/backend
   ```

4. **Executar o Maven Dockerizado**

   ```bash
   mvn spring-boot:run
   ```

   > A aplicação estará disponível em `http://localhost:8080`

   **Observação:** Caso enfrente problemas de permissão, tente remover o parâmetro `-u $(id -u):$(id -g)` do alias ou ajuste as permissões conforme necessário.

### 3️⃣ **Configurar o Frontend (Vue.js)**

1. Certifique-se de que o Node.js está instalado em sua máquina.

2. Navegue até a pasta `frontend`:

   ```bash
   cd ../frontend
   ```

3. Instale as dependências do projeto:

   ```bash
   npm install
   ```

4. Inicie a aplicação Vue.js:

   ```bash
   npm run serve
   ```

   > A aplicação estará disponível em `http://localhost:8081`

### 4️⃣ **Configurar o Banco de Dados (PostgreSQL)**

1. Execute o PostgreSQL via Docker:

   ```bash
   docker run --name aracar-postgres -e POSTGRES_PASSWORD=senha -p 5432:5432 -d postgres
   ```

2. Configure o banco de dados no `application.properties` do Spring Boot:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
   spring.datasource.username=postgres
   spring.datasource.password=senha
   ```

3. (Opcional) Para criar o banco de dados e tabelas automaticamente, você pode configurar o Hibernate:

   ```properties
   spring.jpa.hibernate.ddl-auto=update
   ```

---

## 📝 Explicação dos Passos

### Alias para o Maven Dockerizado

Criar um alias permite que você execute comandos Maven sem ter o Maven ou o Java instalados localmente. O alias configura o ambiente Docker necessário para executar o Maven dentro de um contêiner, mapeando os volumes e portas apropriadas.

### Execução da Aplicação com Docker

Ao executar `mvn spring-boot:run`, a aplicação Spring Boot é iniciada dentro do contêiner Docker, e você pode acessá-la como se estivesse rodando localmente.

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests.

---

## 📄 Licença

Este projeto está sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais informações.

---

## 📞 Contato

Entre em contato com a equipe:

- Gustavo: [email@example.com](mailto:email@example.com)
- Luís: [contato@luisbrescia.tech](mailto:contato@luisbrescia.tech)
- Victor: [email@example.com](mailto:email@example.com)

---

## 🛡️ Boas Práticas

- Certifique-se de que todas as dependências estão atualizadas.
- Verifique se o Docker está em execução antes de iniciar os serviços.
- Utilize variáveis de ambiente para configurações sensíveis, como senhas.

---

## 🧰 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Vue.js**
- **PostgreSQL**
- **Docker**

---

## 📚 Referências

- [Documentação do Spring Boot](https://spring.io/projects/spring-boot)
- [Documentação do Vue.js](https://vuejs.org/)
- [Docker Hub - Maven Image](https://hub.docker.com/_/maven)

---

## 🔄 Atualizações

- **Versão 1.0.0** - Lançamento inicial do projeto.

---

Esperamos que este guia facilite a configuração e execução do nosso projeto. Se tiver alguma dúvida ou problema, não hesite em entrar em contato conosco.

Boas práticas e bom desenvolvimento! 🚀
