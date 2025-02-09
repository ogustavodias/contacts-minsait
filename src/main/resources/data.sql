INSERT INTO
  tb_persons (name, street, postal_code, city, state)
VALUES
  (
    'Gustavo Alves Dias',
    'Rua Cantagalo, 20',
    '00000-000',
    'Sao Paulo',
    'SP'
  ),
  (
    'Eduardo Henrique Marques Ferreira',
    null,
    null,
    null,
    null
  ),
  ('Ramon Alves', null, '11111-111', null, null);

INSERT INTO
  tb_contacts (person_id, contact_type, contact_value)
VALUES
  (1, 'EMAIL', 'gustavo@example.com'),
  (2, 'PHONE', '123456789'),
  (3, 'EMAIL', 'ramonzinho@hotmail.com'),
  (3, 'PHONE', '987654321');