
public class Vector {
	private final int n;
	private double[] data;
	
	public Vector(int n){
		this.n = n;
		this.data = new double[n];
	}
	
	public Vector(double[] data){
		n = data.length;
		this.data = new double[n];
		for(int i=0;i<n;i++){
			this.data[i] = data[i];
		}
	}
	
	
	public int length(){
		return n;
	}
	
	public double dot(Vector that){
		if(this.length() != that.length()){
			throw new IllegalArgumentException("dimensions diagree");
		}
		
		double sum = 0.0;
		for(int i=0;i<n;i++){
			sum = sum +(this.data[i]*that.data[i]);
		}
		return sum;
	}
	
	public double magnitude(){
		return Math.sqrt(this.dot(this));
	}
	
	public double distanceTo(Vector that){
		if(this.length()!=that.length()){
			throw new IllegalArgumentException("dimensions diagree");
		}
		
		return this.minus(that).magnitude();
	}
	
	public Vector minus(Vector that){
		if(this.length() != that.length()){
			throw new IllegalArgumentException("dimensions disagree");
		}
		Vector c = new Vector(n);
		for(int i=0;i<n;i++){
			c.data[i] = this.data[i]-that.data[i];
		}
		return c;		
	}
	
	public Vector plus(Vector that){
		if(this.length()!= that.length()){
			throw new IllegalArgumentException("dimensions disagree`");
		}
		
		Vector c = new Vector(n);
		for(int i=0;i<that.length();i++){
			c.data[i] = this.data[i]+that.data[i];
		}
		return c;
	}
	
	public double cartesian(int i){
		return data[i];
	}
	
	
	@Deprecated
	public Vector times(double factor){
		Vector c = new Vector(n);
		for(int i=0;i<n;i++){
			c.data[i] = factor*data[i];
		}
		return c;
	}
	
	public Vector scale(double factor){
		Vector c= new Vector(n);
		for(int i=0;i<n;i++){
			c.data[i] = factor * data[i];
		}
		return c;
	}
	
	public Vector direction(){
		if(this.magnitude()==0.0){
			throw new ArithmeticException("zero-vector has no direction");
		}
		return this.times(1.0/this.magnitude());
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append('(');
		for(int i=0;i<n;i++){
			s.append(data[i]);
			if(i<n-1) s.append(",  ");
		}
		s.append(')');
		return s.toString();
	}
	
	public static void main(String[] args){
		double[] xdata = {1.0,2.0,3.0};
		double[] ydata = {5.0,2.0,4.0};
		
		Vector x = new Vector(xdata);
		Vector y = new Vector(ydata);
		
		Vector c = x.minus(y);
		c = c.times(2.5);
		System.out.println(c.toString());
	}
	

}
