/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.effectsystem.factories

import org.jetbrains.kotlin.builtins.DefaultBuiltIns
import org.jetbrains.kotlin.effectsystem.impls.ESBooleanConstant
import org.jetbrains.kotlin.effectsystem.impls.ESConstant
import org.jetbrains.kotlin.effectsystem.structure.*
import org.jetbrains.kotlin.types.KotlinType

fun Boolean.lift(): ESBooleanConstant = ESBooleanConstant(ConstantID(this), this)

fun ESBooleanConstant.negate(): ESBooleanConstant = ESBooleanConstant(ConstantID(this.value.not()), this.value.not())

fun Nothing?.lift(): ESConstant = ESConstant(ConstantID(this), this, DefaultBuiltIns.Instance.nullableNothingType)

object ValuesFactory {
    fun createConstant(id: ESValueID, value: Any?, type: KotlinType): ESConstant {
        return if (type == DefaultBuiltIns.Instance.booleanType) {
            ESBooleanConstant(id, value as Boolean)
        } else {
            ESConstant(id, value, type)
        }
    }

    val NOT_NULL_CONSTANT = ESConstant(
            id = NOT_NULL_ID,
            value = object {},
            type = DefaultBuiltIns.Instance.anyType)


    val UNKNOWN_CONSTANT = ESConstant(
            id = UNKNOWN_ID,
            value = object {},
            type = DefaultBuiltIns.Instance.nullableAnyType
    )
}