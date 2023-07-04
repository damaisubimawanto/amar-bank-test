package com.damai.amarbankregistration.dagger

import com.damai.data.mappers.ProvinceDetailResponseToStringMapper
import dagger.Module
import dagger.Provides

/**
 * Created by damai007 on 04/July/2023
 */
@Module
class MapperModule {

    @Provides
    fun provideProvinceDetailResponseToStringMapper(): ProvinceDetailResponseToStringMapper {
        return ProvinceDetailResponseToStringMapper()
    }
}
