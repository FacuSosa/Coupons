package com.aplication.coupons.common.utils

import com.aplication.coupons.R
import com.aplication.coupons.common.entities.CouponEntity
import org.junit.Assert.*
import org.junit.Test

class CouponUtilsKtTest{
    @Test
    fun validateTextCodeSuccessTest(){
        val code = "WELCOME"
        assertTrue(validateTextCode(code))
    }
    @Test
    fun validateTextCodeEmptyFailTest(){
        val code = ""
        assertFalse(validateTextCode(code))
    }
    @Test
    fun validateTextCodeMinLengthTest(){
        val code = "HOLA"
        //val code2 = "HOLA2"
        assertFalse(validateTextCode(code))
        //assertTrue(validateTextCode(code2))
    }
    @Test
    fun validateTextCodeMaxLengthTest(){
        val code = "HOLA_BIENVENIDO_AL_CURSO"
        //val code2 = "HOLA_ALAIN"
        assertFalse(validateTextCode(code))
        //assertTrue(validateTextCode(code2))
    }

    @Test
    fun getMsgErrorByCodeNullTest(){
        val errorCode = null
        val expectedValue = R.string.error_unknow
        val result = getMsgErrorByCode(errorCode)
        assertEquals("Error al evaluar un null.", expectedValue, result)
    }
    @Test
    fun getMsgErrorByCodeExistTest(){
        val errorCode = Constants.ERROR_EXIST
        val expectedValue = R.string.error_unique_code
        val result = getMsgErrorByCode(errorCode)
        assertEquals("Error al evaluar un cupón existente.", expectedValue, result)
    }
    @Test
    fun getMsgErrorByCodeLengthTest(){
        val errorCode = Constants.ERROR_LENGTH
        val expectedValue = R.string.error_invalid_length
        val result = getMsgErrorByCode(errorCode)
        assertEquals("Error al evaluar la longitud válida.", expectedValue, result)
        assertNotEquals("El recurso no existe.", -1, result)
    }

    @Test
    fun checkNotNullCouponTest(){
        val coupon = CouponEntity(code = "ANDROID", description = "Cursos a $9.99 USD")
        assertNotNull("El cupón no debería ser null.", coupon)
    }
    @Test
    fun checkGroupsSuccessTest(){
        val aNames = arrayOf("Alain", "Daniel", "Mary")//valor actual
        val bNames = arrayOf("Alain", "Daniel", "Mary")//valor esperado
        assertArrayEquals("Los arreglos deberían coincidir, revise sus elementos.",
            bNames, aNames)
    }

    @Test
    fun checkNullCouponTest(){
        val coupon = null
        assertNull("El cupón debería ser null.", coupon)
    }
    @Test
    fun checkGroupsFailTest(){
        val aNames = arrayOf("Alain", "Daniel", "Mary")//valor actual
        val bNames = arrayOf("Alain", "Daniela", "Mary")//valor esperado
        assertNotEquals("Estos arreglos coinciden!", bNames, aNames)
    }
}