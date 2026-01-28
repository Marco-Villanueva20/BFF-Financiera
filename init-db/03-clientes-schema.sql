\c "reto-tecnico"
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS clientes (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID,
    nombres VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    tipo_documento VARCHAR(50) NOT NULL,
    numero_documento VARCHAR(20) NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS cuentas (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    cliente_id UUID NOT NULL,
    tipo_cuenta VARCHAR(50) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    saldo DECIMAL(19,2) NOT NULL,

    CONSTRAINT fk_cuentas_cliente
        FOREIGN KEY (cliente_id)
        REFERENCES clientes(id)
        ON DELETE CASCADE
);



CREATE TABLE IF NOT EXISTS cuentas_ahorro (
    cuenta_id UUID PRIMARY KEY,
    plazo_meses INT NOT NULL,
    tasa_interes DECIMAL(5,2) NOT NULL,
    fecha_apertura DATE NOT NULL,

    CONSTRAINT fk_ahorro_cuenta
        FOREIGN KEY (cuenta_id)
        REFERENCES cuentas(id)
        ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS tarjetas_credito (
    cuenta_id UUID PRIMARY KEY,
    numero_tarjeta VARCHAR(20) NOT NULL,
    fecha_expiracion DATE NOT NULL,
    cvv VARCHAR(3) NOT NULL,
    limite_credito DECIMAL(19,2) NOT NULL,

    CONSTRAINT fk_tarjeta_cuenta
        FOREIGN KEY (cuenta_id)
        REFERENCES cuentas(id)
        ON DELETE CASCADE
);
