package br.ufc.cpbr8.util;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

import br.ufc.cpbr8.dao.TweetDAO;
import br.ufc.cpbr8.dao.UsuarioDAO;
import br.ufc.cpbr8.dao.impl.TweetJPADAO;
import br.ufc.cpbr8.dao.impl.UsuarioJPADAO;
import br.ufc.cpbr8.model.Busca;
import br.ufc.cpbr8.model.Tweet;
import br.ufc.cpbr8.model.Usuario;
import sun.awt.windows.ThemeReader;
import twitter4j.HashtagEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.UserMentionEntity;

public class BuscaTweets implements Runnable{

	private Busca busca;
	private Twitter twitter;

	public BuscaTweets(Busca busca, Twitter twitter) {
		this.busca = busca;
		this.twitter = twitter;
	}

	@Override
	public void run() {
		int cont = 1;
		
		while(cont <= busca.getNumBuscas() ){
			Query query = new Query(busca.getValorBusca());
			query.setCount(busca.getQuantidade());
		
			QueryResult resultadoBusca = null;
			
			try {
				resultadoBusca = twitter.search(query);
				List<Status> listaStatus = resultadoBusca.getTweets();
				
				CSVUtil csv = new CSVUtil();
				csv.crirarArquivo(busca.getDataCriacao(), cont);
				
				for (Status status : listaStatus) 
					csv.addlinha(status, busca.getDataCriacao(), cont);
				
				Thread.currentThread().sleep(busca.getTempoBusca());
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				continue;
			}
			cont++;
		}
		
	}

}
