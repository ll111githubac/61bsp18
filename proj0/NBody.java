public class NBody {
    public static double readRadius(String fileName) {
	In in = new In(fileName);
	in.readInt();
	double radius = in.readDouble();
	return radius;
    }
    
    public static Planet[] readPlanets(String fileName) {
	In in = new In(fileName);
	int numPlanets = in.readInt();
	in.readDouble();
	Planet[] ps = new Planet[numPlanets];
	for (int i = 0; i < numPlanets; i++) {
	    double xxPos = in.readDouble();
	    double yyPos = in.readDouble();
    	    double xxVel = in.readDouble();	
    	    double yyVel = in.readDouble();
    	    double mass = in.readDouble();
    	    String img = in.readString();
	    ps[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
	}
	return ps;
    }

    public static void main(String[] args) {
	double T = Double.parseDouble(args[0]);
	double dt = Double.parseDouble(args[1]);
	String filename = args[2];
	Planet[] ps = readPlanets(filename);
	double radius = readRadius(filename);

	StdDraw.setScale(-radius, radius);
	StdDraw.clear();
	StdDraw.picture(0, 0, "images/starfield.jpg");
	for (Planet p : ps) {
	    StdDraw.picture(p.xxPos, p.yyPos, "images/" + p.imgFileName);
	}
	
	StdDraw.enableDoubleBuffering();

	double time = 0;
	while (time < T) {
	    double[] xForces = new double[ps.length];
	    double[] yForces = new double[ps.length];
	    for (int idx = 0; idx < ps.length; idx++) {
		xForces[idx] = ps[idx].calcNetForceExertedByX(ps);
		yForces[idx] = ps[idx].calcNetForceExertedByY(ps);
	    }
	    for (int idx = 0; idx < ps.length; idx++) {
		ps[idx].update(dt, xForces[idx], yForces[idx]);
	    }
	    StdDraw.clear();
	    StdDraw.picture(0, 0, "images/starfield.jpg");
	    for (Planet p : ps) {
		//StdDraw.picture(p.xxPos, p.yyPos, "images/" + p.imgFileName);
		p.draw();
	    }
	    StdDraw.show();
	    StdDraw.pause(10);
	    time += dt;
	}
	StdOut.printf("%d\n", ps.length);
	StdOut.printf("%.2e\n", radius);
	for (int i = 0; i < ps.length; i++) {
	    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		          ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
                          ps[i].yyVel, ps[i].mass, ps[i].imgFileName);   
	}

    }

}	









