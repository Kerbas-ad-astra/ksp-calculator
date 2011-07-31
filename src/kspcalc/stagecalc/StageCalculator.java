package kspcalc.stagecalc;

import kspcal.utils.KSPConfig;

public class StageCalculator {
	public static StageCalc getStageCalculator() {
		StageCalc calc;
		if (KSPConfig.getConfig().hasDirectory()) {
			calc = new StageCalculatorCustom();
		} else {
			calc = new StageCalculatorVanilla();
		}
		return calc;
		
	}
}
