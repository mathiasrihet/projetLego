package lego;

public class Test {
	private int sent_color = 4;
	private int actual_color = 0;
	private int[][] position = {{4, 0},{3,1}};
	private int[][] obstacle = {{4, 5},{5, 4}};


		
		/// Gestion des variables ///
		
		//Getter & Setter pour actual_color
		public void setColorA(int n){
			this.actual_color = n;
		}
		
		public int getColorA(){
			return this.actual_color;
		}
		
		//Getter & Setter pour sent_color
		public void setColorS(int n){
			this.sent_color = n;
		}
		
		public int getColorS(){
			return this.sent_color;
		}
		
		//Setter pour position
		public void setPos(int[][] pos){
			this.position = pos;
		}
		
		public int[][] getPos(){
			//return {{this.position[0][0], this.position[0][1];
			return this.position;
		}
		
		//Setter pour obstacle
		public void setObstacle(int[][] pos){
			this.obstacle = pos;
		}

	   
		
		
		/// Methodes de mouvement ///
		private void travel(double unite) {

	    	//Gestion de coordonnées
	      	this.position[0][Utils.is_parallel_to(position)] += unite*Utils.direction(this.position);
	      	this.position[1][Utils.is_parallel_to(position)] += unite*Utils.direction(this.position);}

		private void turn180(int[][] position) {
			//Partie gestion de coordonnées
			int direction = Utils.direction(this.position);
			if(Utils.is_parallel_to(this.position)==0) {
				this.position[1][0] += 2*direction;
				this.position[1][1] += -2*direction;
			}else{
				this.position[1][0] += 2*direction;
				this.position[1][1] += 2*direction;
			}
			
			
		}
		
		private void run() {
			System.out.println(actual_color);
			System.out.println(sent_color);
			
			//Le robot cherche la case la plus proche de la couleur demandée (non-occupée)
          	int [] destination = Utils.lookFor(this.sent_color, this.position, this.obstacle);
          	System.out.println(destination[0]+"  "+destination[1]);
          	
			while(this.sent_color != this.actual_color) {

				if(this.position[0][Utils.is_parallel_to(this.position)] != destination[Utils.is_parallel_to(this.position)]) {
					if (Math.abs(destination[Utils.is_parallel_to(this.position)]-this.position[0][Utils.is_parallel_to(this.position)])>Math.abs(destination[Utils.is_parallel_to(this.position)]-this.position[1][Utils.is_parallel_to(this.position)])) {
		          		System.out.println("parallèle to "+Utils.is_parallel_to(this.position));
		          		this.turn180(this.position);
		          		System.out.println("tour à 180");
		          		System.out.println(this.position[0][0] +" "+this.position[0][1]+" "+this.position[1][0]+" "+this.position[1][1]);
		          	}
		          	
		          	this.travel(Math.abs(destination [Utils.is_parallel_to(position)]-position[0][Utils.is_parallel_to(position)]));
		          	System.out.println(destination[0]+"  "+destination[1]);
		          	System.out.println("parallèle to "+Utils.is_parallel_to(this.position));
		          	System.out.println(this.position[0][0] +" "+this.position[0][1]+" "+this.position[1][0]+" "+this.position[1][1]);
					
				}else {
					
				}
				
	            
			
	          	
	    
			}
		}
		

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test robot = new Test();
		
		robot.run();
            
	}

}
