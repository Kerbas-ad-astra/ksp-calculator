package kspcalc.math;

import kspcal.utils.Constants;

public class EllipticOrbit {
	private double ApoVel;	// Apogee Velocity
	private double PerVel;	// Perigee Velocity
	private double ApoAlt;	// Apogee Altitude
	private double PerAlt;	// Perigee Altitude
	private double E;		// Eccentricity
	private double period;	// Orbital Period
	
	/**
	 * @return the period
	 */
	public double getPeriod() {
		return period;
	}

	private double a;		// Helper variables
	
	private EllipticOrbitCalcType calcType;	// Input Value type

	/**
	 * @param apoVel
	 * @param perVel
	 * @param calcType
	 */
	public EllipticOrbit(double firstValue, double secondValue,
			EllipticOrbitCalcType calcType) {
		super();
		this.calcType = calcType;
		try {
			this.doMath(firstValue, secondValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void doMath(double firstValue, double secondValue) throws Exception {
		switch (this.calcType) {
		case PERALT_APOALT:
			this.PerAlt = firstValue + Constants.RADIUS;
			this.ApoAlt = secondValue + Constants.RADIUS;
			this.doPerAltApoAltMath();
			break;
		case PERVEL_APOVEL:
			this.PerVel = firstValue;
			this.ApoVel = secondValue;
			this.doPerVelApoVelMath();
			break;
		case PERALT_PERVEL:
			this.PerAlt = firstValue + Constants.RADIUS;
			this.PerVel = secondValue;
			this.doPerAltPerVelMath();
			break;
		case APOALT_APOVEL:
			this.ApoAlt = firstValue + Constants.RADIUS;
			this.ApoVel = secondValue;
			this.doApoAltApoVelMath();
			break;
		default:
			throw new Exception();
		}
		this.doEMath();
		this.doPeroid();
	}
	
	private void doPeroid() {
		this.period = 2d * Math.PI * Math.sqrt(Constants.cube(this.a / 2d) / Constants.GM) / 60d;
	}
	
	private void doPerAltApoAltMath() {
		this.a = this.PerAlt + this.ApoAlt;
		this.doApoVelMath();
		this.doPerVelMath();
	}
	
	private void doPerVelApoVelMath() {
		this.doApoAltMath();
		this.doPerAltMath();
		this.a = this.PerAlt + this.ApoAlt;
	}
	
	private void doPerAltPerVelMath() {
		this.doApoAltMath();
		this.a = this.PerAlt + this.ApoAlt;
		this.doApoVelMath();
	}
	
	private void doApoAltApoVelMath() {
		this.doPerAltMath();
		this.a = this.PerAlt + this.ApoAlt;
		this.doPerVelMath();
	}
	
	private void doPerVelMath() {
		this.PerVel = Math.sqrt( (2d * Constants.GM * this.ApoAlt) / (this.PerAlt * this.a) );
	}
	
	private void doApoVelMath() {
		this.ApoVel = Math.sqrt( (2d * Constants.GM * this.PerAlt) / (this.ApoAlt * this.a) );
	}
	
	private void doPerAltMath() {
		this.PerAlt = this.ApoAlt / ( ( (2d * Constants.GM) / (this.ApoAlt * Constants.square(this.ApoVel)) ) - 1d);
	}
	
	private void doApoAltMath() {
		this.ApoAlt = this.PerAlt / ( ( (2d * Constants.GM) / (this.PerAlt * Constants.square(this.PerVel)) ) - 1d);
	}
	
	private void doEMath() {
		this.E = (this.PerAlt * Constants.square(this.PerVel)) / Constants.GM - 1d;
	}

	/**
	 * @return the e
	 */
	public double getE() {
		return E;
	}

	/**
	 * @return the apoVel
	 */
	public double getApoVel() {
		return ApoVel;
	}

	/**
	 * @return the perVel
	 */
	public double getPerVel() {
		return PerVel;
	}

	/**
	 * @return the apoAlt
	 */
	public double getApoAlt() {
		return ApoAlt;
	}

	/**
	 * @return the perAlt
	 */
	public double getPerAlt() {
		return PerAlt;
	}
	
	
}
