var express = require('express');
var app = express();
var faker = require('faker');
var _ = require('lodash');


var generarUsuario2 = function(){
  var randomId = faker.random.number();
  var randomNombre = faker.name.findName();
  var randomContenido = faker.lorem.sentence();
  var randomFecha = faker.date.future();
  var randomImagen = faker.image.avatar();
  return {
    Id: randomId,
    Nombre: randomNombre,
    Contenido: randomContenido,
    Fecha: randomFecha,
    Imagen: randomImagen
  }

}


var generarUsuario = function(){
  var randomName = faker.name.findName();
  var randomEmail = faker.internet.email();
  var randomImage = faker.image.avatar();
  return {
    nombre: randomName,
    email: randomEmail,
    imagen: randomImage
  }

}


app.get('/', function (req, res) {
  res.send('Mi primer servidor!');
})

app.get('/friends', function (req, res) {
  var cantidad = _.random(5,10)
  var usuarios = _.times(cantidad, generarUsuario)
  res.json(usuarios);
})

app.get('/Reto1', function (req, res) {
  var cantidad2 = _.random(4,4)
  var usuarios2 = _.times(cantidad2, generarUsuario2)
  res.json(usuarios2);
})

app.get('/amigos', function (req, res) {
  res.send('Mis amigos');
})


app.listen(3000);