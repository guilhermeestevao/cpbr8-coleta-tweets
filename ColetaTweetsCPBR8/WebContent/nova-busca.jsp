<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="index.jsp"> Index </a>
	<div>
		<h2>Buscando Tweets!</h2>
		<form action="ServletColetaTweets" method="post">
			<label for="descricao"> Descrição da busca:</label> <br>
			<textarea rows="5" cols="50" name="descricao"></textarea><br>
			<label for="busca"> Buscar por:</label> <br>
			<textarea rows="8" cols="50" name="busca"></textarea><br>
			<label for="tempo_periodo"> Periodo de tempo para buscas: </label>
			<select name="tempo_periodo" id="tempo_periodo"> 
				<option value="minuto"> Minuto </option>
				<option value="dia"> Dia </option>
				<option value="hora"> Hora </option>
			</select>
			<input type="text" name="tempo_hora" size="8px"/> <br>
			<label for="quantidade"> Quantidade de tweets por busca: </label>
			<input type="text" name="quantidade"/> <br>
			<label for="num_buscas"> Reqpetições da busca: </label>
			<input type="text" name="num_buscas" id="num_buscas"/> <br>
			<input type="submit" value="Procurar" />
		</form>
	</div>
</body>
</html>