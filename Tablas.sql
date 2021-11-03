CREATE TABLE Stock (
  Cproducto INT NOT NULL PRIMARY KEY,
  Cantidad INT);
  
CREATE TABLE Pedido (
  Cpedido INT NOT NULL PRIMARY KEY,
  Ccliente INT
  Fecha_pedido DATE);
  
CREATE TABLE DetallePedido(
  Cpedido INT REFERENCES Pedido(Cpedido),
  Cproducto INT REFERENCES Stock(Cproducto),
  Cantidad INT,
  PRIMARY KEY (Cpedido, Cproducto));
