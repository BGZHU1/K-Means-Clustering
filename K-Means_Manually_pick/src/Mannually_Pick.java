import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Mannually_Pick {
	
	public static void main(String[] args) throws FileNotFoundException {
		//read data
		Scanner scan=new Scanner(new File("seeds_dataset.txt"));
		//initialize 2D array
		float [][] data = new float[210][8]; 
		for(int row=0;row<data.length;row++){
			for(int col=0;col<data[0].length;col++){
					data[row][col]=Float.parseFloat(scan.next());
				}
		
			}
		
		//area 14,16,20
		//perimeter 14,15,17
		//compactness - equal 0.82/0.86/0.90
		//LengthKernal -5.5/6.0/6.5
		//width 3.0,3.6,3.8
		//Asymmetry - 2/4/6
		//LengthKernalGrave 5.0/5.5/6.0
		
		//key drive area 
		//key drive perimeter
		//others using mean
		
		
		
		
		//group1 - area driven-corrlation with perimeter
		/*float [] k1={14,14,(float) 0.87099,(float) 5.628533, (float) 3.258605,(float) 3.700201,(float) 5.408071};
		float [] k2={17,15,(float) 0.87099,(float) 5.628533, (float) 3.258605,(float) 3.700201,(float) 5.408071};
		float [] k3={19,17,(float) 0.87099,(float) 5.628533, (float) 3.258605,(float) 3.700201,(float) 5.408071};*/
		
		
		//group2 asymmetry driven only
		float [] k1={17,15,(float) 0.87,(float) 5.628533, (float) 3.2,(float) 2,(float) 5.408071};
		float [] k2={17,15,(float) 0.87,(float) 5.628533, (float) 3.2,(float) 4,(float) 5.408071};
		float [] k3={17,15,(float) 0.87,(float) 5.628533, (float) 3.2,(float) 6,(float) 5.408071};
		
		//group3 - all factors facted in with correlation
		/*float [] k1={14,14,(float) 0.82,(float) 5.5, (float) 3,(float) 2,(float) 5};
		float [] k2={17,15,(float) 0.86,(float) 6.0, (float) 3.6,(float) 4,(float) 5.5};
		float [] k3={19,17,(float) 0.9,(float) 6.5, (float) 3.8,(float) 6,(float) 6};*/
	
		//create three arraylist - to store different data points in clusters
		
		ArrayList<Integer> arr1=new ArrayList();
		ArrayList<Integer> arr2=new ArrayList();
		ArrayList<Integer> arr3=new ArrayList();

		
		//calculate the each xi points distances between 3 clusters
		//assign the xi to one cluster(arraylist)
		
		for(int i=0;i<data.length;i++){
			float distance_to_k1= (float) Math.sqrt(Math.pow(k1[0]-data[i][0],2)+Math.pow(k1[1]-data[i][1],2)+
					Math.pow(k1[2]-data[i][2],2)+Math.pow(k1[3]-data[i][3],2)+Math.pow(k1[4]-data[i][4],2)+
					Math.pow(k1[5]-data[i][5],2)+Math.pow(k1[6]-data[i][6],2));
			
		    float distance_to_k2=(float) Math.sqrt(Math.pow(k2[0]-data[i][0],2)+Math.pow(k2[1]-data[i][1],2)+
			Math.pow(k2[2]-data[i][2],2)+Math.pow(k2[3]-data[i][3],2)+Math.pow(k2[4]-data[i][4],2)+
			Math.pow(k2[5]-data[i][5],2)+Math.pow(k2[6]-data[i][6],2));
		    
		    float distance_to_k3=(float) Math.sqrt(Math.pow(k3[0]-data[i][0],2)+Math.pow(k3[1]-data[i][1],2)+
			Math.pow(k3[2]-data[i][2],2)+Math.pow(k3[3]-data[i][3],2)+Math.pow(k3[4]-data[i][4],2)+
			Math.pow(k3[5]-data[i][5],2)+Math.pow(k3[6]-data[i][6],2));
		    
		    //find the min distance of the three
		    float localMin=Math.min(distance_to_k1, distance_to_k2);
		    float globalMin=Math.min(distance_to_k3, localMin);
		  
		    if(globalMin==distance_to_k1){arr1.add(i);}//add the row number- represent the 7 dimension data point
		    else if(globalMin==distance_to_k2){arr2.add(i);}
		    else{arr3.add(i);} //each row represents a multi-dimensional data
		    
		}
		
	   
		
		//calculate the xi new distances between 3 clusters
	    //assign the xi to one cluster(arraylist)
		//if xi changes the cluster,set the change variable to true
	    //calculate the new mean
		//repeat till change vairable is false
	    
	    boolean change_cluster=true;
		
	    //new means
	    float [] newK1=null;
	    float [] newK2=null;
	    float [] newK3=null;
	    
	
	    
	    while(change_cluster==true){
	    	
			//for each cluster,calculate the mean
			//change the centriod=mean
			
	    	
		    //for cluster 1
		    newK1=new float[7];//new mean
		    
		    for(int features=0;features<7;features++){
		    	float sum1=0;
		    for(int arr_index=0;arr_index<arr1.size();arr_index++){
		        sum1+=data[(int) arr1.get(arr_index)][features];
		    	}
		    
		       float average1=sum1/arr1.size();
		       newK1[features]=average1;
		   
		        
		    	}
		    
		    //for cluster 2
		    newK2=new float[7];
		    for(int features=0;features<7;features++){
		    	float sum2=0;
		    for(int arr_index=0;arr_index<arr2.size();arr_index++){
		        sum2+=data[(int) arr2.get(arr_index)][features];
		    	}
		   
		       float average2=sum2/arr2.size();
		       newK2[features]=average2;
		   
		    	}
		    
		    
		    //for cluster 3
		    newK3=new float[7];
		    for(int features=0;features<7;features++){
		    	float sum3=0;
		    for(int arr_index=0;arr_index<arr3.size();arr_index++){
		        sum3+=data[(int) arr3.get(arr_index)][features];
		    	}
		    
		       float average3=sum3/arr3.size();
		       newK3[features]=average3;
		   
		    	}
		    
		 
		
		//restablish the change value to false;
		change_cluster=false;
	    
	    //three new cluster holders
			ArrayList<Integer> newArr1=new ArrayList<Integer>();
			ArrayList<Integer> newArr2=new ArrayList<Integer>();
			ArrayList<Integer> newArr3=new ArrayList<Integer>();
			
		for(int i=0;i<data.length;i++){
			float distance_to_k1=(float) Math.sqrt(Math.pow(newK1[0]-data[i][0],2)+Math.pow(newK1[1]-data[i][1],2)+
					Math.pow(newK1[2]-data[i][2],2)+Math.pow(newK1[3]-data[i][3],2)+Math.pow(newK1[4]-data[i][4],2)+
					Math.pow(newK1[5]-data[i][5],2)+Math.pow(newK1[6]-data[i][6],2));
			
		    float distance_to_k2=(float) Math.sqrt(Math.pow(newK2[0]-data[i][0],2)+Math.pow(newK2[1]-data[i][1],2)+
			Math.pow(newK2[2]-data[i][2],2)+Math.pow(newK2[3]-data[i][3],2)+Math.pow(newK2[4]-data[i][4],2)+
			Math.pow(newK2[5]-data[i][5],2)+Math.pow(newK2[6]-data[i][6],2));
		    
		    float distance_to_k3=(float) Math.sqrt(Math.pow(newK3[0]-data[i][0],2)+Math.pow(newK3[1]-data[i][1],2)+
			Math.pow(newK3[2]-data[i][2],2)+Math.pow(newK3[3]-data[i][3],2)+Math.pow(newK3[4]-data[i][4],2)+
			Math.pow(newK3[5]-data[i][5],2)+Math.pow(newK3[6]-data[i][6],2));
		    
		    //find the min distance of the three
		    float localMin=Math.min(distance_to_k1, distance_to_k2);
		    float globalMin=Math.min(distance_to_k3, localMin);
		  
		    if(globalMin==distance_to_k1){newArr1.add(i);
		    if(arr1.contains(i)==false){change_cluster=true;}
		    }//add the row number- represent the 7 dimension data point
		    else if(globalMin==distance_to_k2){newArr2.add(i);
		    if(arr2.contains(i)==false){change_cluster=true;}}
		    else{newArr3.add(i);
		    if(arr3.contains(i)==false){change_cluster=true;}}
		    
		}
		
		
		/*System.out.println("old arrs");
	    System.out.println(arr1);
	    System.out.println(arr2);
	    System.out.println(arr3);
		
		System.out.println("new arrs");
	    System.out.println(newArr1);
	    System.out.println(newArr2);
	    System.out.println(newArr3);
	    */
	    
	   //make the newArr1 to old Arr1 - deep copy
	    arr1=new ArrayList(newArr1);
	    arr2=new ArrayList(newArr2);
	    arr3=new ArrayList(newArr3);
	    
	    }//while
	    
	    
	  //calculate the IV and EV after convergence
	    float IV=0;
	    //cluster one
	    for(Object item : arr1 ){
	    	IV+=(float) Math.sqrt(Math.pow(newK1[0]-data[(int) item][0],2)+Math.pow(newK1[1]-data[(int) item][1],2)+
					Math.pow(newK1[2]-data[(int) item][2],2)+Math.pow(newK1[3]-data[(int) item][3],2)+Math.pow(newK1[4]-data[(int) item][4],2)+
					Math.pow(newK1[5]-data[(int) item][5],2)+Math.pow(newK1[6]-data[(int) item][6],2));
	    	
	    }
	    //System.out.println(IV);

	    //cluster two
	    
	    for(Object item2 : arr2){
	    	IV+=(float) Math.sqrt(Math.pow(newK2[0]-data[(int) item2][0],2)+Math.pow(newK2[1]-data[(int) item2][1],2)+
					Math.pow(newK2[2]-data[(int) item2][2],2)+Math.pow(newK2[3]-data[(int) item2][3],2)+Math.pow(newK2[4]-data[(int) item2][4],2)+
					Math.pow(newK2[5]-data[(int) item2][5],2)+Math.pow(newK2[6]-data[(int) item2][6],2));
	    	
	    }
	   // System.out.println(IV);

	    //cluster three
	    for(Object item3: arr3 ){
	    	IV+=(float) Math.sqrt(Math.pow(newK3[0]-data[(int) item3][0],2)+Math.pow(newK3[1]-data[(int) item3][1],2)+
					Math.pow(newK3[2]-data[(int) item3][2],2)+Math.pow(newK3[3]-data[(int) item3][3],2)+Math.pow(newK3[4]-data[(int) item3][4],2)+
					Math.pow(newK3[5]-data[(int) item3][5],2)+Math.pow(newK3[6]-data[(int) item3][6],2));
	    	
	    }
	    
	    
	    
	    //EV
	    //start with cluster one -to every point in cluster 2/3
	    float EV=0;
	    //for cluster 1 to 2
	   for(Object item1 : arr1){
		   for(Object item2 : arr2){
			   EV+=(float) Math.sqrt(Math.pow(data[(int)item1][0]-data[(int) item2][0],2)+Math.pow(data[(int)item1][1]-data[(int) item2][1],2)+
						Math.pow(data[(int)item1][2]-data[(int) item2][2],2)+Math.pow(data[(int)item1][3]-data[(int) item2][3],2)+Math.pow(data[(int)item1][4]-data[(int) item2][4],2)+
						Math.pow(data[(int)item1][5]-data[(int) item2][5],2)+Math.pow(data[(int)item1][6]-data[(int) item2][6],2));
			    }
	    	 
	    }
	
	   //for cluster 1 to 3
	   for(Object item1 : arr1){
		   for(Object item3 : arr3){
			   EV+=(float) Math.sqrt(Math.pow(data[(int)item1][0]-data[(int) item3][0],2)+Math.pow(data[(int)item1][1]-data[(int) item3][1],2)+
						Math.pow(data[(int)item1][2]-data[(int) item3][2],2)+Math.pow(data[(int)item1][3]-data[(int) item3][3],2)+Math.pow(data[(int)item1][4]-data[(int) item3][4],2)+
						Math.pow(data[(int)item1][5]-data[(int) item3][5],2)+Math.pow(data[(int)item1][6]-data[(int) item3][6],2));
			    }
	    	 
	    }

	   //for cluster 2 to 3
	   for(Object item2: arr2){
		   for(Object item3 : arr3){
			   EV+=(float) Math.sqrt(Math.pow(data[(int)item2][0]-data[(int) item3][0],2)+Math.pow(data[(int)item2][1]-data[(int) item3][1],2)+
						Math.pow(data[(int)item2][2]-data[(int) item3][2],2)+Math.pow(data[(int)item2][3]-data[(int) item3][3],2)+Math.pow(data[(int)item2][4]-data[(int) item3][4],2)+
						Math.pow(data[(int)item2][5]-data[(int) item3][5],2)+Math.pow(data[(int)item2][6]-data[(int) item3][6],2));
			    }
	    	 
	    }
	   
	   
	   EV=EV/3;
	   float Measure=IV/EV;
	   System.out.println();
	   System.out.println("The Value of IV is : "+IV);
	   System.out.println("The Value of EV is : "+EV);
	   System.out.println("The Value of IV/EV is : "+Measure);

	   

	   System.out.println("Centriod 1 :");
	   for(int i=0;i<7;i++){
		   System.out.print(newK1[i]+" ");
	   }
	   
	   System.out.println();
	   System.out.println("Centriod 2 :");

	   for(int i=0;i<7;i++){
		   System.out.print(newK2[i]+" ");
	   }
	   
	   System.out.println();
	   System.out.println("Centriod 3 :");


	   for(int i=0;i<7;i++){
		   System.out.print(newK3[i]+" ");
	   }
	   System.out.println();
	   System.out.println("----------------------------");
	} 
		
		}//main

		

