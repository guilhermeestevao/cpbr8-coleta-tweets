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
		
		long ultimaBusca = 0;
		
		while(cont <= busca.getNumBuscas() ){
			Query query = new Query(busca.getValorBusca());
			//query.setCount(100);
			query.setLang("pt");
			QueryResult resultadoBusca = null;
			
			try {
				resultadoBusca = twitter.search(query);
				
				CSVUtil csv = new CSVUtil();
				csv.crirarArquivo(busca.getDataCriacao(), cont);
				
				int quantidade = 0;
				
				while(resultadoBusca.hasNext()){
					query = resultadoBusca.nextQuery();
					
					if(cont != 1)
						query.setSinceId(ultimaBusca);
					
					
					resultadoBusca = twitter.search(query);
					
					ultimaBusca = System.currentTimeMillis();
					
					List<Status> listaStatus = resultadoBusca.getTweets();
					
					quantidade+=listaStatus.size();
					
					for (Status status : listaStatus) 
						csv.addlinha(status, busca.getDataCriacao(), cont);
					
					if(quantidade >= busca.getQuantidade())
						break;
					
				}
				
				
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
