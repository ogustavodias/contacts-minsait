INSERT INTO
  tb_persons (name, street, postal_code, city, state)
VALUES
  (
    'Gustavo Alves Dias',
    'Rua Cantagalo, 20',
    '00000000',
    'São Paulo',
    'SP'
  ),
  (
    'Eduardo Henrique Marques Ferreira',
    'Rua Samba, 40',
    '11111111',
    'Ourinhos',
    'SP'
  ),
  (
    'Ramon Alves',
    'Barão de Piracicaba, 9000',
    '22222222',
    'São Paulo',
    'SP'
  );

INSERT INTO
  tb_contacts (person_id, contact_type, contact_value)
VALUES
  (1, 'EMAIL', 'gustavo@example.com'),
  (2, 'PHONE', '1140228922'),
  (3, 'EMAIL', 'ramonzinho@hotmail.com'),
  (3, 'PHONE', '11987654321');