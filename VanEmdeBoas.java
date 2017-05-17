
public class VanEmdeBoas {
	
	private int u;       //number range
	private int max, min;
	private int upperRoot, lowerRoot;
	
	private VanEmdeBoas summary;
	private VanEmdeBoas[] cluster;
	
	
	public VanEmdeBoas(int size){
		u = hiPowerOfTwo(size);//in case u isn't a pwr of 2

		//calculate the high & low roots
        double sqRoot = Math.sqrt(u);
		upperRoot = hiPowerOfTwo(sqRoot);
		lowerRoot= lowPowerOfTwo(sqRoot);


		if(u > 2) {
			summary = new VanEmdeBoas(upperRoot);
			cluster = new VanEmdeBoas[upperRoot];
			for(int i=0; i<upperRoot; i++)
				cluster[i] = new VanEmdeBoas(lowerRoot);//loop creates structures
		}

		max = min = -1; // -1 means null
	}
	
	private int hiPowerOfTwo(double n){
		double power =1;
		while(power<n){
			power *= 2;
		}
		return (int)power;
	}
	
	private int lowPowerOfTwo(double n){
		double power = 1;
		while(power*2 <= n){
			power *= 2;
		}
		return (int)power;
	}
	
	public static int vebMinimum(VanEmdeBoas tree){
		return tree.min;
	}
	
	public static int vebMaximum(VanEmdeBoas tree){
		return tree.max;
	}
	
	private int high(int x){
		return (int)x/lowerRoot;                         //here I don't try to do floor
	}
	
	private int low(int x){
		return x%lowerRoot;
	}
	
	private int index(int x, int y){
		return x*lowerRoot+y;
	}
	
	public static boolean isMember(VanEmdeBoas tree,int x){
		if(x==tree.min || x==tree.max){
			return true;
		}else if(tree.u==2){
			return false;
		}else{
			return isMember(tree.cluster[tree.high(x)],tree.low(x));
		}
	}
	
	
	public static void vebEmptyInsert(VanEmdeBoas tree, int x){
		tree.min = x;
		tree.max = x;
	}
	
	public static void vebTreeInsert(VanEmdeBoas tree, int x){
		if(tree.min == -1){
			vebEmptyInsert(tree,x);
		}else{
			if(x<tree.min){
				int tmp = tree.min;
				tree.min = x;
				x = tmp;
			}
			if(tree.u > 2){
				int index = tree.high(x);
//				System.out.println("U :" +tree.u);
//			    System.out.println("high: "+index);
//			    System.out.println("cluster size:" +tree.cluster.length);	
				if(vebMinimum(tree.cluster[tree.high(x)])==-1){
					vebTreeInsert(tree.summary,tree.high(x));
					vebEmptyInsert(tree.cluster[tree.high(x)],tree.low(x));
				}else{
					vebTreeInsert(tree.cluster[tree.high(x)],tree.low(x));
				}
			}
			if(x>tree.max){
				tree.max = x;
			}
		}
	}
	
	public static int successor(VanEmdeBoas tree, int x){
		 if(tree.u == 2){
			 if( x==0 && tree.max == 1){
				 return 1;
			 }else{
				 return -1;
			 }
		 }else if(tree.min!= -1 && x<tree.min){
			 return tree.min;
		 }else{
			 int index = tree.high(x);
			 int recX = tree.low(x);
			 int maxVal = vebMaximum(tree.cluster[index]);
			 if(maxVal != -1 && recX < maxVal){
				 int offset = successor(tree.cluster[index],recX);
				 return tree.index(index, offset);
			 }else{
				 int succ_cluster = successor(tree.summary,index);
				 if(succ_cluster == -1){
					 return -1;
				 }else{
					 int offset = vebMinimum(tree.cluster[succ_cluster]);
					 return tree.index(succ_cluster, offset);							 
				 }
				 
			 }
		 }
	}
	
	public static int predecessor(VanEmdeBoas tree, int x){
		if(tree.u==2){
			if( x==1 && tree.min == 0){
				return 0;
			}else{
				return -1;
			}
		}else if(tree.max!=-1 && x > tree.max){
			return tree.max;
		}else{
			int index = tree.high(x);
			int recX = tree.low(x);
			int min_low = vebMinimum(tree.cluster[index]);
			if(min_low != -1 && recX>min_low){
				int offset = predecessor(tree.cluster[index],recX);
				return tree.index(index,offset);
			}else{
				int pre_cluster = predecessor(tree.summary,index);
				if(pre_cluster == -1){
					if(tree.min != -1 && x>tree.min){
						return tree.min;
					}else{
						return -1;
					}
				}else{
					int offset = vebMaximum(tree.cluster[pre_cluster]);
					return tree.index(pre_cluster, offset);
				}
			}
			
		}
	}
	
	public static void vebDelete(VanEmdeBoas tree,int x){
		if(tree.min == tree.max){
			tree.min = -1;
			tree.max = -1;
		}else if(tree.u==2){
			if(x==tree.min){
				tree.min = tree.max;
			}else{
				tree.max = tree.min;
			}
		}else{
			if( x == tree.min){
				int first_cluster = vebMinimum(tree.summary);
				x = tree.index(first_cluster, vebMinimum(tree.cluster[first_cluster]));
				tree.min = x;
			}
			
			int clusterIndex = tree.high(x);
			vebDelete(tree.cluster[clusterIndex],tree.low(x));
			if(vebMinimum(tree.cluster[clusterIndex])==-1){
				vebDelete(tree.summary,clusterIndex);
				if(x == tree.max){
					int summary_max = vebMaximum(tree.summary);
					if(summary_max == -1){
						tree.max = tree.min;
					}else{
						tree.max = tree.index(summary_max, vebMaximum(tree.cluster[summary_max]));
					}
				}
			}else if(x == tree.max){
				tree.max = tree.index(clusterIndex, vebMaximum(tree.cluster[clusterIndex]));
			}
		}
	}
	
	
	public static void main(String[] args){
		VanEmdeBoas tree = new VanEmdeBoas(128);
		for(int i=5;i<50;i++){
			vebTreeInsert(tree,i);
		}
		for(int i=5;i<50;i++){
			boolean res = isMember(tree,i);
			System.out.println(res);
		}
		for(int i=30;i<50;i++){
			vebDelete(tree, i);
		}
		System.out.println("After Delete");
		for(int i=30;i<50;i++){
			boolean res = isMember(tree,i);
			System.out.println(res);
		}
		

	}

}
