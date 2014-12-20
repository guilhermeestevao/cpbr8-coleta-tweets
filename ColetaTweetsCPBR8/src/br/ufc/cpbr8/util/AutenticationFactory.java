package br.ufc.cpbr8.util;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class AutenticationFactory {

	private static final String CONSUMERKEY = "NeA3aSlFvNiAOfBpR2iygvkT5";
	private static final String CONSUMERSECRET = "Z8gVO20T08kWAjJ30jV9YPaSzewe7Wczo3kD0dRCyV4KQRs0tT";
	private static final String ACCESSTOKEN = "275657169-6dHPXNfQXLPK1ZuoVtbxLMSDiRvJSuuY1Avd5lOS";
	private static final String ACCESSTOKENSECRET = "pwQ0QdXdCldbfvDwdKrmSmp1t2FPfY7j9f3RKpePhuc6Z";

	public static Twitter getTwitter(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey(CONSUMERKEY)
		.setOAuthConsumerSecret(CONSUMERSECRET)
		.setOAuthAccessToken(ACCESSTOKEN)
		.setOAuthAccessTokenSecret(ACCESSTOKENSECRET);
		TwitterFactory twitterFactory = new TwitterFactory(cb.build());
		Twitter twitter = twitterFactory.getInstance();
		return twitter;
	}

}
