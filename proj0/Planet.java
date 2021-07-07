public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
	          double yV, double m, String img) {
	xxPos = xP;
	yyPos = yP;
	xxVel = xV;
	yyVel = yV;
	mass = m;
	imgFileName = img;
    }

    public Planet(Planet p) {
	this.xxPos = p.xxPos;
	this.yyPos = p.yyPos;
	this.xxVel = p.xxVel;
	this.yyVel = p.yyVel;
	this.mass = p.mass;
	this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
	double dx = this.xxPos - p.xxPos;
	double dy = this.yyPos - p.yyPos;
	double rSquare = dx * dx + dy * dy;
	double r = Math.sqrt(rSquare);
	return r;
    }

    public double calcForceExertedBy(Planet p) {
	double r = calcDistance(p);
	return G * p.mass * this.mass / (r*r);
    }

    public double calcForceExertedByX(Planet p) {
	double distanceX = p.xxPos - this.xxPos;
	double force = calcForceExertedBy(p);
	double r = calcDistance(p);
	return force * distanceX / r;
    }

    public double calcForceExertedByY(Planet p) {
	double distanceY = p.yyPos - this.yyPos;
	double force = calcForceExertedBy(p);
	double r = calcDistance(p);
	return force * distanceY / r;
    }

    public double calcNetForceExertedByX(Planet[] ps) {
	double netForceX = 0;
	for (Planet p : ps) {
	    if (p.xxPos != this.xxPos) {
		netForceX += calcForceExertedByX(p);
	    }
	}
	return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] ps) {
	double netForceY = 0;
	for (Planet p : ps) {
	    if (p.yyPos != this.yyPos) {
		netForceY += calcForceExertedByY(p);
	    }
	}
	return netForceY;
    }

    public void update(double dt, double xF, double yF) {
	double xAcc = xF / mass;
	double yAcc = yF / mass;
	xxVel = xxVel + dt * xAcc;
	yyVel = yyVel + dt * yAcc;
	xxPos = xxPos + dt * xxVel;
	yyPos = yyPos + dt * yyVel;
    }

   public void draw() {
       StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
   }

}
