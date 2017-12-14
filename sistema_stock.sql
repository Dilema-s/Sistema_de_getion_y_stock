-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-06-2017 a las 22:10:43
-- Versión del servidor: 5.7.14
-- Versión de PHP: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistema_stock`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `apellido` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `mail` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` int(30) NOT NULL,
  `direccion` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  `dni` int(8) NOT NULL,
  `cuentaCorriente` varchar(1) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `nombre`, `apellido`, `mail`, `telefono`, `direccion`, `dni`, `cuentaCorriente`) VALUES
(1, 'Consumidor Final', 'Consumidor Final', '@', 0, 'NO', 0, 'n'),
(2, 'natu', 'morizon', 'yo@mail.com', 1234567, 'españa 58', 3244567, 's'),
(4, 'chabe ', 'contrera', 'yo@mail.com', 467382, 'rivadavia 345', 6543123, 's'),
(7, 'collar ', 'morizon', 'yo@mail.com', 1212123, 'españa 45', 1212112, 'n'),
(8, 'sddsd', 'sdsdsd', 'yo@mail.com', 343434, 'sdsd 4', 3434343, ''),
(10, 'cxcxc', 'xcxcxc', 'dfdfdf@dfdf.com', 1212121, 'xcxcxc 55', 1121212, ''),
(12, 'matI', 'ferrero', 'mati@gmail.com', 15611707, 'saenz diaz 572', 31566730, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentacorriente`
--

CREATE TABLE `cuentacorriente` (
  `id_cuentaCorriente` int(11) NOT NULL,
  `cliente` int(11) NOT NULL,
  `venta` int(11) NOT NULL,
  `baja` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `cuentacorriente`
--

INSERT INTO `cuentacorriente` (`id_cuentaCorriente`, `cliente`, `venta`, `baja`) VALUES
(1, 2, 2, b'1'),
(2, 4, 6, b'0'),
(3, 4, 14, b'0'),
(4, 2, 17, b'1'),
(5, 4, 18, b'0'),
(6, 4, 21, b'0'),
(7, 4, 23, b'0'),
(8, 4, 28, b'0'),
(9, 4, 31, b'0'),
(10, 2, 32, b'1'),
(11, 2, 34, b'1'),
(12, 4, 35, b'1'),
(13, 4, 60, b'0'),
(14, 4, 61, b'1'),
(15, 2, 69, b'1'),
(16, 4, 70, b'1'),
(17, 4, 71, b'1'),
(18, 4, 72, b'1'),
(19, 4, 73, b'0'),
(20, 2, 74, b'1'),
(21, 4, 78, b'1'),
(22, 2, 80, b'1'),
(23, 2, 81, b'1'),
(24, 4, 90, b'1'),
(25, 2, 109, b'0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datosmonotributo`
--

CREATE TABLE `datosmonotributo` (
  `razonSocial` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  `cuit` bigint(20) NOT NULL,
  `calle` varchar(70) COLLATE utf8_spanish_ci NOT NULL,
  `numero` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `piso` int(3) DEFAULT NULL,
  `dpto` varchar(1) COLLATE utf8_spanish_ci DEFAULT NULL,
  `localidad` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `provincia` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `cp` int(11) NOT NULL,
  `cai` bigint(20) NOT NULL,
  `inicioActividades` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `datosmonotributo`
--

INSERT INTO `datosmonotributo` (`razonSocial`, `cuit`, `calle`, `numero`, `piso`, `dpto`, `localidad`, `provincia`, `cp`, `cai`, `inicioActividades`) VALUES
('Software', 20315667306, 'Saenz Dias', '572', 0, '', 'Rafaela', 'Santa Fe', 2300, 2342232323232323, '2017-06-01');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `numeroFactura` bigint(20) NOT NULL,
  `venta` int(11) NOT NULL,
  `datosMonotributo` bigint(20) NOT NULL,
  `condicionDeVenta` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`numeroFactura`, `venta`, `datosMonotributo`, `condicionDeVenta`) VALUES
(1, 53, 20315667306, 'efectivo'),
(2, 54, 20315667306, 'efectivo'),
(3, 55, 20315667306, 'efectivo'),
(4, 56, 20315667306, 'efectivo'),
(5, 57, 20315667306, 'efectivo'),
(6, 58, 20315667306, 'efectivo'),
(7, 59, 20315667306, 'efectivo'),
(8, 60, 20315667306, 'cc'),
(9, 61, 20315667306, 'cc'),
(10, 62, 20315667306, 'efectivo'),
(11, 63, 20315667306, 'efectivo'),
(12, 64, 20315667306, 'efectivo'),
(13, 65, 20315667306, 'efectivo'),
(14, 66, 20315667306, 'efectivo'),
(15, 67, 20315667306, 'efectivo'),
(16, 68, 20315667306, 'efectivo'),
(17, 73, 20315667306, 'Cuenta Corriente'),
(18, 74, 20315667306, 'Cuenta Corriente'),
(19, 75, 20315667306, 'efectivo'),
(20, 76, 20315667306, 'efectivo'),
(21, 77, 20315667306, 'efectivo'),
(22, 78, 20315667306, 'Cuenta Corriente'),
(23, 79, 20315667306, 'efectivo'),
(24, 80, 20315667306, 'Cuenta Corriente'),
(25, 81, 20315667306, 'Cuenta Corriente'),
(26, 82, 20315667306, 'efectivo'),
(27, 83, 20315667306, 'efectivo'),
(28, 84, 20315667306, 'efectivo'),
(29, 85, 20315667306, 'efectivo'),
(30, 86, 20315667306, 'efectivo'),
(31, 87, 20315667306, 'efectivo'),
(32, 88, 20315667306, 'efectivo'),
(33, 89, 20315667306, 'efectivo'),
(34, 90, 20315667306, 'Cuenta Corriente'),
(35, 91, 20315667306, 'efectivo'),
(36, 92, 20315667306, 'efectivo'),
(37, 93, 20315667306, 'efectivo'),
(38, 94, 20315667306, 'efectivo'),
(39, 95, 20315667306, 'efectivo'),
(40, 96, 20315667306, 'efectivo'),
(41, 97, 20315667306, 'efectivo'),
(42, 98, 20315667306, 'efectivo'),
(43, 99, 20315667306, 'efectivo'),
(44, 100, 20315667306, 'efectivo'),
(45, 101, 20315667306, 'efectivo'),
(46, 102, 20315667306, 'efectivo'),
(47, 103, 20315667306, 'efectivo'),
(48, 104, 20315667306, 'efectivo'),
(49, 105, 20315667306, 'efectivo'),
(50, 108, 20315667306, 'efectivo'),
(51, 109, 20315667306, 'Cuenta Corriente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimiento`
--

CREATE TABLE `movimiento` (
  `id_movimiento` bigint(20) NOT NULL,
  `fecha` date NOT NULL,
  `cajero` int(11) NOT NULL,
  `motivo` varchar(80) COLLATE utf8_spanish_ci NOT NULL,
  `tipoMovimiento` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `movimiento`
--

INSERT INTO `movimiento` (`id_movimiento`, `fecha`, `cajero`, `motivo`, `tipoMovimiento`, `total`) VALUES
(1, '2017-06-10', 4, 'venta 67', 'INGRESO', 342),
(2, '2017-06-12', 4, 'compra de mercadería', 'egreso', 200),
(3, '2017-06-12', 1, 'Pago de cuenta corriente', 'EGRESO', 300),
(4, '2017-06-12', 1, 'Venta', 'EGRESO', 1000),
(5, '2017-06-12', 1, 'Venta al por mayor', 'EGRESO', 1500),
(6, '2017-06-12', 1, 'ffffff', 'EGRESO', 344),
(7, '2017-06-12', 1, 'venta 12', 'INGRESO', 4000),
(8, '2017-06-10', 1, 'fffdddd', 'INGRESO', 344),
(9, '2017-06-12', 1, 'fdfdfdf', 'INGRESO', 43),
(10, '2017-06-12', 1, 'fdfdf32323', 'INGRESO', 34.2),
(11, '2017-06-14', 1, 'Venta en efectivo N° 101del cliente Consumidor Final', 'INGRESO', 2000),
(12, '2017-06-14', 1, 'Venta en efectivo N° 102del cliente Consumidor Final', 'INGRESO', 1000),
(13, '2017-06-16', 1, 'Pago de cuenta corriente, venta N° 90. Cliente Logica.Cliente@4d0bb403', 'INGRESO', 18650.64),
(14, '2017-06-16', 1, 'Pago de cuenta corriente, venta N° 90. Cliente Logica.Cliente@4d0bb403', 'INGRESO', 18650.64),
(15, '2017-06-16', 1, 'Pago de cuenta corriente, venta N° 71. Cliente Logica.Cliente@4d0bb403', 'INGRESO', 353.5),
(16, '2017-06-16', 1, 'Pago de cuenta corriente, venta N° 81. Cliente morizon, natu', 'INGRESO', 57),
(17, '2017-06-16', 1, 'Pago de cuenta corriente, venta N° 78. Cliente contrera, chabe ', 'INGRESO', 58.65),
(18, '2017-06-15', 1, 'venta al por mayor', 'INGRESO', 3444),
(19, '2017-06-16', 1, 'Pago de cuenta corriente, venta N° 69. Cliente morizon, natu', 'INGRESO', 353.5),
(20, '2017-06-16', 1, 'Pago de cuenta corriente, venta N° 72. Cliente contrera, chabe ', 'INGRESO', 353.5),
(21, '2017-06-16', 1, 'Pago de cuenta corriente, venta N° 74. Cliente morizon, natu', 'INGRESO', 424.2),
(22, '2017-06-16', 1, 'Pago de cuenta corriente, venta N° 61. Cliente contrera, chabe ', 'INGRESO', 293.9),
(23, '2017-06-16', 1, 'Pago de cuenta corriente, venta N° 2. Cliente morizon, natu', 'INGRESO', 34),
(24, '2017-06-18', 1, 'Pago de cuenta corriente, venta N° 70. Cliente contrera, chabe ', 'INGRESO', 353.5),
(25, '2017-06-18', 1, 'Venta en efectivo N° 103. Cliente Consumidor Final', 'INGRESO', 672.19),
(26, '2017-06-18', 1, 'Venta en efectivo N° 104. Cliente Consumidor Final', 'INGRESO', 72.44),
(27, '2017-06-18', 1, 'Venta en efectivo N° 105. Cliente morizon', 'INGRESO', 267.75),
(28, '2017-06-18', 1, 'pago de productos', 'EGRESO', 1500),
(29, '2017-06-18', 1, 'Pago de cuenta corriente, venta N° 80. Cliente morizon, natu', 'INGRESO', 23),
(30, '2017-06-18', 2, 'Venta en efectivo N° 108. Cliente contrera', 'INGRESO', 1000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product`
--

CREATE TABLE `product` (
  `id_producto` int(30) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `precio` float NOT NULL,
  `descripcion` text COLLATE utf8_spanish_ci NOT NULL,
  `proovedor` int(11) NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `product`
--

INSERT INTO `product` (`id_producto`, `nombre`, `precio`, `descripcion`, `proovedor`, `stock`) VALUES
(1, 'cable', 70.7, 'cable HDMI', 2, 13),
(2, 'Pc notebook', 9325.32, 'pc notebook compaq origen Argentina', 2, 2),
(3, 'tele', 1000, 'tele cuadrado antiguo panasonic', 3, 42),
(4, 'diario', 5.5, 'diario Castellano de Rafela', 3, 41),
(5, 'almohada ', 150.75, 'almohada ajustable al cuerpo de tela blanca', 3, 40),
(6, 'Lapiz', 3.4, 'Lapiz faber punta roja', 3, 39),
(7, 'mouse', 100, 'mouse optico gris y neggro', 3, 38),
(8, 'Parlantes', 57, 'blancos panasonic', 1, 37),
(9, 'auriculares', 50, 'negros panasonic', 1, 36),
(10, 'Mic', 20, 'blancos panasonic', 2, 46),
(11, 'taza porcelana', 23, 'dsdsdsdsd', 1, 45),
(12, 'Lapicera bic', 20.5, 'Lapicera bic azul trazo grueso', 1, 44),
(13, 'canopla', 57, 'Canopla de cuero con un dibujo de la renga', 3, 24),
(14, 'Botella ', 15.44, 'Botella plástica color naranja', 2, 25),
(15, 'Diccionario', 461.44, 'Diccionario de la lengua española, kapeluz', 2, 26),
(16, 'Reloj', 210.75, 'Reloj despertador de metal a pilas', 2, 27),
(17, 'collar ', 35.65, 'collar verde para perros', 2, 28),
(18, 'Perfume', 23, 'sdsdsd', 1, 31),
(19, 'Ibuprofeno', 25, '400 mg', 4, 32),
(20, 'CD music', 150, 'CD the best songs of reggae', 1, 33),
(21, 'DVD', 200, 'DVD de Credeence', 5, 34);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proovedor`
--

CREATE TABLE `proovedor` (
  `id_proovedor` int(11) NOT NULL,
  `razon_social` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `mail` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  `plazoDeEntrega` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `proovedor`
--

INSERT INTO `proovedor` (`id_proovedor`, `razon_social`, `direccion`, `telefono`, `mail`, `plazoDeEntrega`) VALUES
(1, 'Casual', '-', '-', '-', 0),
(2, 'estelares sA', 'luna park 54', '1234567', 'yo@mail.com', 0),
(3, 'natalita SACIFIA', 'españa 58', '422301', 'natalita@mail.com', 0),
(4, 'Jose SA', 'rafaela', '7676767', 'yo@mail.com', 0),
(5, 'Mateyco S.R.L.', 'Villa Los Alamos 583', '21345666', 'mateyco@mail.com', 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `provincias`
--

CREATE TABLE `provincias` (
  `id_provincia` int(11) NOT NULL,
  `provincia` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `provincias`
--

INSERT INTO `provincias` (`id_provincia`, `provincia`) VALUES
(1, 'Santa Fe'),
(2, 'Salta'),
(3, 'Corrientes'),
(4, 'Entre Ríos'),
(5, 'Mendoza'),
(6, 'Jujuy'),
(7, 'Tucumán'),
(8, 'San Juan'),
(9, 'San Luis'),
(10, 'Chaco'),
(11, 'Catamarca'),
(12, 'Misiones'),
(13, 'Córdoba'),
(14, 'Buenos Aires'),
(15, 'Neuquén'),
(16, 'Río Negro'),
(17, 'Chubut'),
(18, 'Formosa'),
(19, 'Santiago del Estero'),
(20, 'La Pampa'),
(21, 'Santa Cruz'),
(22, 'La Rioja'),
(23, 'Tierra del Fuego');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pruebalector`
--

CREATE TABLE `pruebalector` (
  `codigo` bigint(20) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `precio` double NOT NULL,
  `proovedor` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `pruebalector`
--

INSERT INTO `pruebalector` (`codigo`, `nombre`, `precio`, `proovedor`) VALUES
(7793360982309, 'Arvejas La campagnola', 15.45, 'La campagnola');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `stock`
--

CREATE TABLE `stock` (
  `stock` int(11) NOT NULL,
  `min_stock` int(11) NOT NULL,
  `id_stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `stock`
--

INSERT INTO `stock` (`stock`, `min_stock`, `id_stock`) VALUES
(21, 5, 1),
(1, 1, 2),
(19, 4, 3),
(52, 4, 5),
(22, 2, 6),
(22, 4, 7),
(22, 42, 8),
(22, 2, 9),
(22, 2, 10),
(22, 2, 11),
(22, 2, 12),
(6, 1, 13),
(22, 34, 14),
(22, 21, 15),
(22, 13, 16),
(22, 12, 17),
(22, 12, 18),
(22, 12, 19),
(22, 12, 20),
(22, 12, 21),
(22, 12, 22),
(22, 12, 23),
(18, 2, 24),
(21, 2, 25),
(21, 2, 26),
(10, 2, 27),
(3, 2, 28),
(3, 2, 29),
(2, 2, 30),
(22, 1, 31),
(8, 2, 32),
(7, 2, 33),
(10, 2, 34),
(23, 2, 35),
(20, 2, 36),
(12, 3, 37),
(12, 3, 38),
(12, 3, 39),
(12, 3, 40),
(12, 3, 41),
(12, 3, 42),
(12, 3, 43),
(12, 3, 44),
(12, 3, 45),
(12, 3, 46);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ultimo_id_producto_guardado`
--

CREATE TABLE `ultimo_id_producto_guardado` (
  `ultimo_id` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `ultimo_id_producto_guardado`
--

INSERT INTO `ultimo_id_producto_guardado` (`ultimo_id`, `id`) VALUES
(13, 1),
(15, 2),
(16, 3),
(17, 4),
(18, 5),
(19, 6),
(20, 7),
(21, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `apellido` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `usuario` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `contraseña` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `esAdmin` varchar(1) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`nombre`, `apellido`, `usuario`, `contraseña`, `id_usuario`, `esAdmin`) VALUES
('mati', 'ferrero', 'admin', 'admin', 1, 'S'),
('natu', 'morizon', 'natu', '123', 2, 'N'),
('chiquito', 'morizon', 'chiquito', '1234', 4, '0'),
('collar', 'morizon', 'collar', '12345', 8, '0'),
('lizy', 'ferrero', 'orejotas', '5721', 9, '0'),
('richard', 'ferrero', 'siames', '1234', 10, '0'),
('yayito', 'leon', 'yayo', '6767', 11, '0'),
('lyzi', 'ferrero', 'patotas', '9999', 15, 's'),
('Anahi', 'Acevedo', 'Anahi', '7373', 27, 's');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `id_venta` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `cliente` int(11) NOT NULL,
  `total` double NOT NULL,
  `cajero` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`id_venta`, `fecha`, `cliente`, `total`, `cajero`) VALUES
(1, '2017-05-02', 4, 15.6, 4),
(2, '2017-05-03', 10, 34, 11),
(3, '2017-05-17', 12, 100, 1),
(5, '2017-05-29', 2, 461.44, 11),
(6, '2017-05-29', 4, 269.4, 2),
(8, '2017-05-30', 4, 28, 1),
(11, '2017-05-30', 4, 150.75, 1),
(13, '2017-05-31', 4, 77.5, 1),
(14, '2017-05-31', 4, 20.939999999999998, 1),
(15, '2017-05-31', 1, 1005.5, 1),
(16, '2017-05-31', 2, 15.44, 1),
(17, '2017-05-31', 2, 461.44, 1),
(18, '2017-05-31', 4, 1005.5, 1),
(19, '2017-05-31', 7, 156.25, 1),
(20, '2017-05-31', 4, 476.88, 1),
(21, '2017-05-31', 4, 476.88, 1),
(22, '2017-05-31', 1, 2000, 1),
(23, '2017-05-31', 4, 476.88, 1),
(24, '2017-05-31', 1, 20.939999999999998, 1),
(25, '2017-05-31', 1, 464.84, 1),
(26, '2017-06-01', 1, 15.44, 1),
(27, '2017-06-01', 7, 75, 1),
(28, '2017-06-01', 4, 210.75, 1),
(29, '2017-06-01', 1, 481.94, 1),
(30, '2017-06-01', 8, 78.65, 1),
(31, '2017-06-01', 4, 350, 1),
(32, '2017-06-01', 2, 48, 1),
(33, '2017-06-01', 2, 38.44, 1),
(34, '2017-06-01', 2, 1000, 1),
(35, '2017-06-01', 4, 214.15, 1),
(36, '2017-06-01', 7, 114, 1),
(37, '2017-06-01', 1, 2000, 1),
(38, '2017-06-01', 1, 2005.5, 1),
(39, '2017-06-01', 8, 6.8, 1),
(40, '2017-06-01', 1, 77.5, 1),
(41, '2017-06-01', 1, 40, 1),
(42, '2017-06-02', 1, 5.5, 1),
(43, '2017-06-02', 1, 5.5, 1),
(44, '2017-06-02', 1, 461.44, 1),
(45, '2017-06-02', 1, 246.4, 1),
(46, '2017-06-02', 4, 123, 1),
(47, '2017-06-02', 1, 400, 1),
(48, '2017-06-02', 1, 672.19, 1),
(49, '2017-06-02', 1, 80, 1),
(50, '2017-06-02', 1, 92.65, 2),
(51, '2017-06-02', 1, 461.44, 1),
(52, '2017-06-04', 1, 521.84, 1),
(53, '2017-06-04', 1, 254.15, 1),
(54, '2017-06-04', 7, 261.84, 1),
(55, '2017-06-04', 4, 71.3, 1),
(56, '2017-06-04', 1, 482.38, 1),
(57, '2017-06-04', 1, 103.4, 1),
(58, '2017-06-04', 2, 160.4, 1),
(59, '2017-06-04', 2, 10476.07, 1),
(60, '2017-06-04', 4, 159.65, 1),
(61, '2017-06-04', 4, 293.9, 1),
(62, '2017-06-04', 1, 1124.44, 1),
(63, '2017-06-04', 1, 260.95, 1),
(64, '2017-06-04', 1, 557.48, 1),
(65, '2017-06-04', 8, 37377.479999999996, 1),
(66, '2017-06-05', 1, 169.59, 1),
(67, '2017-06-05', 1, 40.839999999999996, 1),
(68, '2017-06-05', 1, 494.90000000000003, 1),
(69, '2017-06-05', 2, 353.5, 1),
(70, '2017-06-05', 4, 353.5, 1),
(71, '2017-06-05', 4, 353.5, 1),
(72, '2017-06-05', 4, 353.5, 1),
(73, '2017-06-05', 4, 15.44, 1),
(74, '2017-06-05', 2, 424.2, 1),
(75, '2017-06-05', 1, 141.4, 1),
(76, '2017-06-05', 1, 57, 2),
(77, '2017-06-05', 4, 230.75, 2),
(78, '2017-06-05', 4, 58.65, 2),
(79, '2017-06-05', 1, 570, 2),
(80, '2017-06-05', 2, 23, 2),
(81, '2017-06-05', 2, 57, 2),
(82, '2017-06-05', 1, 75.44, 1),
(83, '2017-06-05', 1, 161.9, 1),
(84, '2017-06-05', 1, 1400, 1),
(85, '2017-06-05', 1, 600, 1),
(86, '2017-06-06', 1, 270.7, 1),
(87, '2017-06-06', 1, 1470.7, 1),
(88, '2017-06-06', 1, 9325.32, 1),
(89, '2017-06-06', 1, 9325.32, 1),
(90, '2017-06-06', 4, 18650.64, 1),
(91, '2017-06-06', 1, 16.5, 1),
(92, '2017-06-06', 1, 212.10000000000002, 1),
(93, '2017-06-06', 1, 141.4, 1),
(94, '2017-06-06', 1, 70.7, 1),
(95, '2017-06-06', 1, 76.2, 1),
(96, '2017-06-06', 2, 5.5, 1),
(97, '2017-06-08', 1, 170.5, 1),
(98, '2017-06-08', 1, 55.65, 1),
(99, '2017-06-08', 1, 92, 1),
(100, '2017-06-08', 1, 50, 1),
(101, '2017-06-14', 1, 2000, 1),
(102, '2017-06-14', 1, 1000, 1),
(103, '2017-06-18', 1, 672.19, 1),
(104, '2017-06-18', 1, 72.44, 1),
(105, '2017-06-18', 2, 267.75, 1),
(106, '2017-06-18', 2, 1057, 2),
(107, '2017-06-18', 4, 1000, 2),
(108, '2017-06-18', 4, 1000, 2),
(109, '2017-06-18', 2, 1057, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta_producto`
--

CREATE TABLE `venta_producto` (
  `id_venta_producto` int(11) NOT NULL,
  `id_venta` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `venta_producto`
--

INSERT INTO `venta_producto` (`id_venta_producto`, `id_venta`, `id_producto`, `cantidad`) VALUES
(1, 2, 1, 0),
(2, 5, 15, 0),
(3, 6, 16, 0),
(4, 6, 17, 0),
(5, 6, 18, 0),
(6, 8, 4, 0),
(7, 8, 4, 0),
(8, 8, 6, 0),
(9, 8, 6, 0),
(10, 8, 6, 0),
(11, 8, 6, 0),
(12, 8, 6, 0),
(13, 11, 5, 0),
(14, 13, 13, 5),
(15, 13, 12, 0),
(16, 15, 4, 10),
(17, 15, 3, 0),
(18, 16, 14, 0),
(19, 19, 4, 0),
(20, 19, 5, 0),
(21, 22, 3, 0),
(22, 22, 3, 0),
(23, 24, 4, 0),
(24, 24, 14, 0),
(25, 25, 15, 0),
(26, 25, 6, 0),
(27, 26, 14, 0),
(28, 27, 19, 0),
(29, 27, 19, 0),
(30, 27, 19, 0),
(31, 26, 17, 0),
(32, 29, 12, 0),
(33, 29, 15, 0),
(34, 30, 11, 0),
(35, 30, 10, 0),
(36, 30, 17, 0),
(37, 33, 11, 0),
(38, 33, 14, 0),
(39, 34, 21, 0),
(40, 34, 21, 0),
(41, 34, 21, 0),
(42, 34, 21, 0),
(43, 34, 21, 0),
(44, 35, 16, 0),
(45, 35, 6, 0),
(46, 36, 13, 0),
(47, 36, 13, 0),
(48, 37, 3, 0),
(49, 37, 3, 0),
(50, 38, 3, 0),
(51, 38, 3, 0),
(52, 38, 4, 0),
(53, 39, 6, 0),
(54, 39, 6, 0),
(55, 40, 13, 0),
(56, 40, 12, 0),
(57, 41, 10, 0),
(58, 41, 10, 0),
(59, 44, 15, 1),
(60, 45, 17, 1),
(61, 45, 16, 1),
(62, 46, 7, 1),
(63, 46, 18, 1),
(64, 47, 21, 1),
(65, 47, 21, 1),
(66, 48, 15, 1),
(67, 48, 16, 1),
(68, 49, 18, 1),
(69, 49, 8, 1),
(70, 50, 17, 1),
(71, 50, 8, 1),
(72, 51, 15, 1),
(73, 52, 15, 1),
(74, 52, 13, 1),
(75, 52, 6, 1),
(76, 53, 5, 1),
(77, 53, 6, 1),
(78, 53, 7, 1),
(79, 54, 14, 1),
(80, 54, 16, 1),
(81, 54, 17, 1),
(82, 55, 17, 1),
(83, 55, 17, 1),
(84, 56, 15, 1),
(85, 56, 4, 1),
(86, 56, 14, 1),
(87, 57, 6, 1),
(88, 57, 7, 1),
(89, 58, 7, 1),
(90, 58, 6, 1),
(91, 58, 8, 1),
(92, 59, 5, 1),
(93, 59, 2, 1),
(94, 59, 3, 1),
(95, 60, 6, 1),
(96, 60, 5, 1),
(97, 60, 4, 1),
(98, 61, 4, 1),
(99, 61, 6, 1),
(100, 61, 8, 5),
(101, 61, 8, 5),
(102, 61, 8, 5),
(103, 61, 8, 5),
(104, 61, 8, 5),
(105, 62, 5, 3),
(106, 62, 5, 3),
(107, 62, 5, 3),
(108, 62, 15, 1),
(109, 62, 16, 1),
(110, 63, 5, 1),
(111, 63, 6, 3),
(112, 63, 6, 3),
(113, 63, 6, 3),
(114, 63, 7, 1),
(115, 64, 15, 1),
(116, 64, 14, 6),
(117, 64, 14, 6),
(118, 64, 14, 6),
(119, 64, 14, 6),
(120, 64, 14, 6),
(121, 64, 14, 6),
(122, 64, 6, 1),
(123, 65, 4, 1),
(124, 65, 1, 1),
(125, 65, 2, 4),
(126, 66, 14, 1),
(127, 66, 5, 1),
(128, 66, 6, 1),
(129, 67, 14, 1),
(130, 67, 4, 4),
(131, 67, 6, 1),
(132, 68, 1, 7),
(133, 69, 1, 5),
(134, 70, 1, 5),
(135, 71, 1, 5),
(136, 72, 1, 5),
(137, 73, 14, 1),
(138, 74, 1, 6),
(139, 75, 1, 2),
(140, 76, 13, 1),
(141, 77, 16, 1),
(142, 77, 10, 1),
(143, 78, 17, 1),
(144, 78, 18, 1),
(145, 79, 8, 10),
(146, 80, 18, 1),
(147, 81, 8, 1),
(148, 82, 14, 1),
(149, 82, 10, 3),
(150, 83, 1, 1),
(151, 83, 12, 1),
(152, 83, 1, 1),
(153, 84, 21, 7),
(154, 85, 21, 3),
(155, 86, 1, 1),
(156, 86, 21, 1),
(157, 87, 21, 7),
(158, 87, 1, 1),
(159, 88, 2, 1),
(160, 89, 2, 1),
(161, 90, 2, 2),
(162, 91, 4, 3),
(163, 92, 1, 3),
(164, 93, 1, 2),
(165, 94, 1, 1),
(166, 95, 1, 1),
(167, 95, 4, 1),
(168, 96, 4, 1),
(169, 97, 12, 1),
(170, 97, 20, 1),
(171, 98, 10, 1),
(172, 98, 17, 1),
(173, 99, 18, 4),
(174, 100, 9, 1),
(175, 101, 3, 2),
(176, 102, 3, 1),
(177, 103, 15, 1),
(178, 103, 16, 1),
(179, 104, 13, 1),
(180, 104, 14, 1),
(181, 105, 13, 1),
(182, 105, 16, 1),
(183, 106, 13, 1),
(184, 106, 3, 1),
(185, 107, 3, 1),
(186, 108, 3, 1),
(187, 109, 13, 1),
(188, 109, 3, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`);

--
-- Indices de la tabla `cuentacorriente`
--
ALTER TABLE `cuentacorriente`
  ADD PRIMARY KEY (`id_cuentaCorriente`),
  ADD KEY `cliente` (`cliente`),
  ADD KEY `venta` (`venta`);

--
-- Indices de la tabla `datosmonotributo`
--
ALTER TABLE `datosmonotributo`
  ADD PRIMARY KEY (`cuit`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`numeroFactura`),
  ADD KEY `venta` (`venta`),
  ADD KEY `datosMonotributo` (`datosMonotributo`);

--
-- Indices de la tabla `movimiento`
--
ALTER TABLE `movimiento`
  ADD PRIMARY KEY (`id_movimiento`),
  ADD KEY `cajero` (`cajero`);

--
-- Indices de la tabla `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `proovedor` (`proovedor`),
  ADD KEY `stock` (`stock`);

--
-- Indices de la tabla `proovedor`
--
ALTER TABLE `proovedor`
  ADD PRIMARY KEY (`id_proovedor`);

--
-- Indices de la tabla `provincias`
--
ALTER TABLE `provincias`
  ADD PRIMARY KEY (`id_provincia`);

--
-- Indices de la tabla `pruebalector`
--
ALTER TABLE `pruebalector`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`id_stock`);

--
-- Indices de la tabla `ultimo_id_producto_guardado`
--
ALTER TABLE `ultimo_id_producto_guardado`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `usuario` (`usuario`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`id_venta`),
  ADD KEY `cliente` (`cliente`),
  ADD KEY `cajero` (`cajero`);

--
-- Indices de la tabla `venta_producto`
--
ALTER TABLE `venta_producto`
  ADD PRIMARY KEY (`id_venta_producto`),
  ADD KEY `id_venta` (`id_venta`),
  ADD KEY `id_producto` (`id_producto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT de la tabla `cuentacorriente`
--
ALTER TABLE `cuentacorriente`
  MODIFY `id_cuentaCorriente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `numeroFactura` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;
--
-- AUTO_INCREMENT de la tabla `movimiento`
--
ALTER TABLE `movimiento`
  MODIFY `id_movimiento` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT de la tabla `product`
--
ALTER TABLE `product`
  MODIFY `id_producto` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT de la tabla `proovedor`
--
ALTER TABLE `proovedor`
  MODIFY `id_proovedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `provincias`
--
ALTER TABLE `provincias`
  MODIFY `id_provincia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT de la tabla `stock`
--
ALTER TABLE `stock`
  MODIFY `id_stock` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;
--
-- AUTO_INCREMENT de la tabla `ultimo_id_producto_guardado`
--
ALTER TABLE `ultimo_id_producto_guardado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `id_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=110;
--
-- AUTO_INCREMENT de la tabla `venta_producto`
--
ALTER TABLE `venta_producto`
  MODIFY `id_venta_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=189;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuentacorriente`
--
ALTER TABLE `cuentacorriente`
  ADD CONSTRAINT `cuentacorriente_ibfk_1` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`id_cliente`),
  ADD CONSTRAINT `cuentacorriente_ibfk_2` FOREIGN KEY (`venta`) REFERENCES `venta` (`id_venta`);

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`venta`) REFERENCES `venta` (`id_venta`),
  ADD CONSTRAINT `factura_ibfk_2` FOREIGN KEY (`datosMonotributo`) REFERENCES `datosmonotributo` (`cuit`);

--
-- Filtros para la tabla `movimiento`
--
ALTER TABLE `movimiento`
  ADD CONSTRAINT `movimiento_ibfk_2` FOREIGN KEY (`cajero`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_4` FOREIGN KEY (`proovedor`) REFERENCES `proovedor` (`id_proovedor`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `product_ibfk_5` FOREIGN KEY (`stock`) REFERENCES `stock` (`id_stock`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `venta_ibfk_2` FOREIGN KEY (`cajero`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `venta_producto`
--
ALTER TABLE `venta_producto`
  ADD CONSTRAINT `venta_producto_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id_venta`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `venta_producto_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `product` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
