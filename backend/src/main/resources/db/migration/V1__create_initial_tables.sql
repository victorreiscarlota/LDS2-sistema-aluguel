-- Criação da tabela de Automóveis
CREATE TABLE IF NOT EXISTS automovel (
    id BIGSERIAL PRIMARY KEY,
    ano INTEGER NOT NULL,
    marca VARCHAR(255),
    matricula VARCHAR(255),
    modelo VARCHAR(255),
    placa VARCHAR(255)
);

-- Criação da tabela de Clientes
CREATE TABLE IF NOT EXISTS cliente (
    id BIGSERIAL PRIMARY KEY,
    cpf VARCHAR(255),
    endereco VARCHAR(255),
    nome VARCHAR(255),
    profissao VARCHAR(255),
    rg VARCHAR(255)
);

-- Criação da tabela de Contratos
CREATE TABLE IF NOT EXISTS contrato (
    id BIGSERIAL PRIMARY KEY,
    data_contrato DATE,
    tipo VARCHAR(255),
    credito_id BIGINT,
    CONSTRAINT fk_credito FOREIGN KEY (credito_id) REFERENCES credito(id)
);

-- Criação da tabela de Crédito
CREATE TABLE IF NOT EXISTS credito (
    id BIGSERIAL PRIMARY KEY,
    data_concessao DATE,
    valor FLOAT4 NOT NULL
);

-- Criação da tabela de Pedidos de Aluguel
CREATE TABLE IF NOT EXISTS pedido_aluguel (
    id BIGSERIAL PRIMARY KEY,
    data_pedido DATE,
    status VARCHAR(255),
    cliente_id BIGINT,
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

-- Tabela de relacionamento entre contrato e automóveis
CREATE TABLE IF NOT EXISTS contrato_automoveis (
    contrato_id BIGINT NOT NULL,
    automoveis_id BIGINT NOT NULL,
    PRIMARY KEY (contrato_id, automoveis_id),
    CONSTRAINT fk_contrato FOREIGN KEY (contrato_id) REFERENCES contrato(id),
    CONSTRAINT fk_automovel FOREIGN KEY (automoveis_id) REFERENCES automovel(id)
);
