package com.hacker.rank.practice.session;
import java.io.*;
import java.util.*;
public class UnigramFrequencies {



		
		public static class Unigram{
			Hashtable<String,Integer> dict;
			
			public Unigram(String filename) throws FileNotFoundException{
				Scanner scan=new Scanner(new File(filename));
				dict=new Hashtable<String,Integer>();
				while(scan.hasNext()){
					dict.put(scan.next(), 1);
				}
				scan.close();
			}
			
			public Unigram(Hashtable<String,Integer> d){
				dict=d;
			}
			
			public ArrayList<String> pickWord(String s){
				ArrayList<String> l=new ArrayList<String>();
				StringBuffer sb=new StringBuffer();
				String key="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-'";
				for(int i=0;i<s.length();i++){
					char c=s.charAt(i);
					if(key.indexOf(c)==-1){
						if(sb.length()>0){
							l.add(sb.toString());
							sb=new StringBuffer();
						}
					}else{
						sb.append(c);
					}
				}
				if(sb.length()!=0) l.add(sb.toString());
				return l;
			}
			
			public void updateFreq(String filename) throws FileNotFoundException{
				Scanner scan=new Scanner(new File(filename));
				while(scan.hasNext()){
					ArrayList<String> stringlist=pickWord(scan.next());
					for(String s:stringlist) if(dict.containsKey(s)) dict.put(s,dict.get(s)+1);
				}
				scan.close();
			}
			
			public Hashtable<String,Integer> matchingEntries(String s){
				Hashtable<String,Integer> table=new Hashtable<String,Integer>();
				for(Map.Entry<String,Integer> e:dict.entrySet())
					if(e.getKey().startsWith(s)) table.put(e.getKey(), e.getValue());
				return table;
			}
			
			public static ArrayList<Map.Entry<String,Integer>> sortTable(Hashtable<String,Integer> table, int n){
				ArrayList<Map.Entry<String,Integer>> list=new ArrayList<Map.Entry<String,Integer>>();
				for(Map.Entry<String,Integer> e:table.entrySet()){
					int i;
					for(i=0;i<list.size()&&i<n;i++){
						if(list.get(i).getValue()==e.getValue()){
							if(list.get(i).getKey().compareTo(e.getKey())>0){
								list.add(i,e);
								break;
							}
						}
						if(list.get(i).getValue()<e.getValue()){
							list.add(i,e);
							break;
						}
					}
					if(i==list.size()) list.add(e);
				}
				return list;
			}
			
		}
		
		public static class InputPredictor{
			Unigram unigram;
			
			public InputPredictor(String dict,String corpus) throws FileNotFoundException{
				unigram=new Unigram(dict);
				unigram.updateFreq(corpus);
			}
			
			public ArrayList<String> keycodeToString(String s){
				String[] key={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"} ;
				ArrayList<String> list=new ArrayList<String>();
				int len=s.length();
				int[] pos=new int[len];
				int[] index=new int[len];
				for(int i=0;i<len;i++){
					pos[i]=s.charAt(i)-48;
					if(pos[i]<2) return list;
					index[i]=0;
				}
				int adv=1,i;
				do{
					StringBuffer sb=new StringBuffer();
					for(i=0;i<len;i++){
						sb.append(key[pos[i]].charAt(index[i]));
					}
					list.add(sb.toString());
					i=len-1;
					do{
						index[i]++;
						adv=0;
						if(index[i]==key[pos[i]].length()){
							index[i]=0;
							i--;
							adv=1;
						}
					}while(adv==1&&i>=0);
				}while(adv==0);
				return list;
			}
			
			public String predict(String s,int n){
				ArrayList<Map.Entry<String,Integer>> list;
				Unigram dict=unigram;
				for(int l=1;l<=s.length();l++){
					String sub=s.substring(0,l);
					ArrayList<String> key=keycodeToString(sub);
					if(key.size()==0) return "No Suggestions";
					Hashtable<String,Integer> temp=new Hashtable<String,Integer>();
					for(int i=0;i<key.size();i++){
						temp.putAll(dict.matchingEntries(key.get(i)));
					}
					dict=new Unigram(temp);
					if(temp.isEmpty()) break;
				}
				list=Unigram.sortTable(dict.dict,n);
				if(list.size()==0) return "No Suggestions";
				StringBuffer sb=new StringBuffer();
				for(int i=0;i<list.size()&&i<5;i++){
					if(i!=0) sb.append(";");
					sb.append(list.get(i).getKey());
				}
				return sb.toString();
			}
		}
		
		
		public static void main(String[] args) throws FileNotFoundException{
			String dict="t9Dictionary";
			String corpus="t9TextCorpus";
			InputPredictor a=new InputPredictor(dict,corpus);
			Scanner scan=new Scanner(new InputStreamReader(System.in));
			int n=Integer.parseInt(scan.nextLine().trim());
			for(int i=0;i<n;i++){
				System.out.println(a.predict(scan.nextLine().trim(), 5));
			}
			scan.close();
		}
		
	}

