
-dontwarn javax.annotation.**


##################################
# Android Support v7 #
##################################
-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }
-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}
-keep class android.support.v7.widget.SearchView { *; }


##################################
# Google Play Service #
##################################
-dontwarn android.support.v4.**
-keep public class com.google.android.gms.* { public *; }
-dontwarn com.google.android.gms.**
-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}


##################################
# GSON google support #
##################################
-keepattributes Signature

-keepattributes *Annotation*

-keep class sun.misc.Unsafe { *; }

-dontwarn sun.misc.**
-keep class com.google.gson.examples.android.model.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keep class com.jutt.example1.model.** { *; }


##################################
# Retrofit 2
# https://github.com/square/retrofit/blob/master/retrofit/src/main/resources/META-INF/proguard/retrofit2.pro
##################################
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions


##################################
# OkHttp
# https://github.com/square/okhttp/blob/master/okhttp/src/main/resources/META-INF/proguard/okhttp3.pro
##################################
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-dontnote okhttp3.**

##################################
# Okio
# https://github.com/square/okio/blob/master/okio/src/jvmMain/resources/META-INF/proguard/okio.pro
##################################
-dontwarn okio.**
-keep class sun.misc.Unsafe { *; }
-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement


-keep interface org.parceler.Parcel
-keep @org.parceler.Parcel class * { *; }
-keep class **$$Parcelable { *; }

-dontwarn java.lang.invoke.*

-keepclassmembers class com.codepath.models** { <fields>; }


##################################
# RX libs #
##################################
# Rxjava-promises
-keep class com.darylteo.rx.** { *; }
-dontwarn com.darylteo.rx.**
-keep class rx.schedulers.Schedulers {
    public static <methods>;
}
-keep class rx.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class rx.schedulers.TestScheduler {
    public <methods>;
}
-keep class rx.schedulers.Schedulers {
    public static ** test();
}

-dontwarn java.lang.invoke.*
-keep class link.styler.styler_android.data.model.** { *; }
-keep class * extends io.reactivex.observers.DisposableObserver {
   *;
}


##################################
# Glide 4
##################################
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}


##################################
# Fabric libs #
##################################
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**


##################################
# Google common libs #
##################################
-keep class com.google.api.services.*.model.*
-keep class com.google.api.client.**
-dontwarn com.google.common.**
-dontwarn com.google.common.collect.MinMaxPriorityQueue

##################################
## Android architecture components: Lifecycle
##################################
# LifecycleObserver's empty constructor is considered to be unused by proguard
-keepclassmembers class * implements androidx.lifecycle.LifecycleObserver {
    <init>(...);
}
# ViewModel's empty constructor is considered to be unused by proguard
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}
# keep Lifecycle State and Event enums values
-keepclassmembers class androidx.lifecycle.Lifecycle$State { *; }
-keepclassmembers class androidx.lifecycle.Lifecycle$Event { *; }
# keep methods annotated with @OnLifecycleEvent even if they seem to be unused
# (Mostly for LiveData.LifecycleBoundObserver.onStateChange(), but who knows)
-keepclassmembers class * {
    @androidx.lifecycle.OnLifecycleEvent *;
}

-keepclassmembers class * implements androidx.lifecycle.LifecycleObserver {
    <init>(...);
}

-keep class * implements androidx.lifecycle.LifecycleObserver {
    <init>(...);
}
-keepclassmembers class androidx.** { *; }
-keepclassmembers class androidx.lifecycle.** { *; }
-keep class androidx.** { *; }
-keep class androidx.lifecycle.** { *; }
-keepnames class androidx.lifecycle.ViewModel
-dontwarn androidx.**
-dontwarn androidx.lifecycle.**

# Other GenerateAdapter
-keep class * implements androidx.lifecycle.GeneratedAdapter {<init>(...);}

##################################
# Koin #
##################################
-keepnames class android.arch.lifecycle.ViewModel
-keepclassmembers public class * extends android.arch.lifecycle.ViewModel { public <init>(...); }
-keepclassmembers class com.lebao.app.domain.** { public <init>(...); }
-keepclassmembers class * { public <init>(...); }