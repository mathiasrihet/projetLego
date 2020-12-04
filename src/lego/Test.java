package lego;

public class Test {
	private int sent_color = 4;
	private int actual_color = 0;
	private int[][] position = {{0, 0},{-1,-1}};
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

		
		private void turn180() {
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
			
			
			
		
		private void turn(int[] destination) {
			int diff = Utils.sign(destination[Utils.isnot_parallel_to(position)]-position[0][Utils.isnot_parallel_to(position)]);
			
			
			if(Utils.is_parallel_to(position)==0) {
				if(diff == Utils.direction(position)) {
					System.out.println("x turn left"); //x+ -2y, x- +2y --> dans 1, -2*sign
					this.position[1][1] += -2*Utils.direction(position);
				}else {
					System.out.println("x turn right");//+2X, -2X --> dans 0, 2*sign
					this.position[1][0] += 2*Utils.direction(position);
				}
			}else {
				if(diff == Utils.direction(position)) {
					System.out.println("y turn right");//y+ +2y, y- -2y --> dans 1, 2*sign
					this.position[1][1] += 2*Utils.direction(position);
					}else {
						System.out.println("y turn left");// +2x, -2x --> dans 0, 2*sign
						this.position[1][0] += 2*Utils.direction(position);
					}
				}
			}
		
		private void run() {
			System.out.println(actual_color);
			System.out.println(sent_color);
			
			//Le robot cherche la case la plus proche de la couleur demandée (non-occupée)
          	int [] destination = Utils.lookFor(this.sent_color, this.position, this.obstacle);
          	System.out.println(destination[0]+"  "+destination[1]);
          	
			while(this.position[0][0]!= destination[0] || this.position[0][1] != destination[1]) {

				if(this.position[0][Utils.is_parallel_to(this.position)] != destination[Utils.is_parallel_to(this.position)]) {
					if (Math.abs(destination[Utils.is_parallel_to(this.position)]-this.position[0][Utils.is_parallel_to(this.position)])>Math.abs(destination[Utils.is_parallel_to(this.position)]-this.position[1][Utils.is_parallel_to(this.position)])) {
		          		System.out.println("parallèle to "+Utils.is_parallel_to(this.position));
		          		this.turn180();
		          		System.out.println("tour à 180");
		          		System.out.println(this.position[0][0] +" "+this.position[0][1]+" "+this.position[1][0]+" "+this.position[1][1]);
		          	}
		          	
					System.out.println("travelled "+ Math.abs(destination [Utils.is_parallel_to(position)]-position[0][Utils.is_parallel_to(position)]));
		          	this.travel(Math.abs(destination [Utils.is_parallel_to(position)]-position[0][Utils.is_parallel_to(position)]));
		          	System.out.println("parallèle to "+Utils.is_parallel_to(this.position));
		          	System.out.println(this.position[0][0] +" "+this.position[0][1]+" "+this.position[1][0]+" "+this.position[1][1]);
					
				}else{
					int diff = Utils.sign(destination[Utils.isnot_parallel_to(position)]-position[0][Utils.isnot_parallel_to(position)]);
					
					
					if(Utils.is_parallel_to(position)==0) {
						if(diff == Utils.direction(position)) {
							System.out.println("x turn left"); //x+ -2y, x- +2y --> dans 1, -2*sign
							this.position[1][1] += -2*Utils.direction(position);
						}else {
							System.out.println("x turn right");//+2X, -2X --> dans 0, 2*sign
							this.position[1][0] += 2*Utils.direction(position);
						}
					}else {
						if(diff == Utils.direction(position)) {
							System.out.println("y turn right");//y+ +2y, y- -2y --> dans 1, 2*sign
							this.position[1][1] += 2*Utils.direction(position);
							}else {
								System.out.println("y turn left");// +2x, -2x --> dans 0, 2*sign
								this.position[1][0] += 2*Utils.direction(position);
							}
						}
					System.out.println(this.position[0][0] +" "+this.position[0][1]+" "+this.position[1][0]+" "+this.position[1][1]);
					}

			}
			System.out.println("success !");
			System.out.println(this.position[0][0] +" "+this.position[0][1]+" "+this.position[1][0]+" "+this.position[1][1]);
		}
		

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test robot = new Test();
		
		robot.run();
            
	}

}
