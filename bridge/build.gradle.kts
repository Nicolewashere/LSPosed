/*
 * This file is part of LSPosed.
 *
 * LSPosed is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LSPosed is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LSPosed.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Copyright (C) 2022 LSPosed Contributors
 */

plugins {
    id("com.android.library")
}

val apiCode: Int by rootProject.extra

android {
    namespace = "org.lsposed.bridge"

    buildFeatures {
        androidResources = false
    }

    defaultConfig {
        consumerProguardFiles("proguard-rules.pro")

        buildConfigField("int", "API_CODE", "$apiCode")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles("proguard-rules.pro")
        }
    }
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("de.upb.cs.swt:axml:2.1.2")
    compileOnly("androidx.annotation:annotation:1.3.0")
    compileOnly(project(":hiddenapi:stubs"))
    implementation(project(":hiddenapi:bridge"))
    implementation(project(":manager-service"))
    implementation(project(":daemon-service"))
}
