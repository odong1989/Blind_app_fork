package com.ksinfo.blind.annualincome;

import com.ksinfo.blind.annualincome.vo.CompanyJobGroupVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CompanyJobGroupApi {
    @GET("company/annualIncome/annualIncomeCalculator")
    Call<List<CompanyJobGroupVO>> getJobGroupListAll();
}
