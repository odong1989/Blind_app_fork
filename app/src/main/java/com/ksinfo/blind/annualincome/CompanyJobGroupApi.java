package com.ksinfo.blind.annualincome;

import com.ksinfo.blind.annualincome.vo.CompanyJobGroupVO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CompanyJobGroupApi {
    @GET("company/annualIncome/annualIncomeCalculator")
    Call<CompanyJobGroupVO> getJobGroupListAll();
}
