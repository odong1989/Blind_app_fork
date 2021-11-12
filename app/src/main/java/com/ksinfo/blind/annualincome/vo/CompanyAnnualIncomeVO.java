package com.ksinfo.blind.annualincome.vo;

public final class CompanyAnnualIncomeVO {
	private final int minAnnualIncome; //직군별 최저임금
	private final int avgAnnualIncome; //직군별 평균임금
	private final int maxAnnualIncome; //직군별 최대임금

	public CompanyAnnualIncomeVO(int minAnnualIncome, int avgAnnualIncome, int maxAnnualIncome) {
		this.minAnnualIncome = minAnnualIncome;
		this.avgAnnualIncome = avgAnnualIncome;
		this.maxAnnualIncome = maxAnnualIncome;
	}

	public int getMinAnnualIncome() {
		return minAnnualIncome;
	}

	public int getAvgAnnualIncome() {
		return avgAnnualIncome;
	}

	public int getMaxAnnualIncome() {
		return maxAnnualIncome;
	}
}