package com.kotlin.lib._2_memberextensionfunction.decompiled;

import org.jetbrains.annotations.NotNull;

class Base {
}
final class Derived extends Base {
}

class BaseCaller {
   public void printFunctionInfo(@NotNull Base base) {
      System.out.println("Base extension function in BaseCaller");
   }

   public void printFunctionInfo(@NotNull Derived derived) {
      System.out.println("Derived extension function in BaseCaller");
   }

   public final void call(@NotNull Base b) {
      this.printFunctionInfo(b);
   }
}

final class DerivedCaller extends BaseCaller {
   public void printFunctionInfo(@NotNull Base base) {
      System.out.println("Base extension function in DerivedCaller");
   }

   public void printFunctionInfo(@NotNull Derived derived) {
      System.out.println("Derived extension function in DerivedCaller");
   }
}

public final class CallerKt {
   public static void main(String[] var0) {
      new BaseCaller().call(new Base());
      new DerivedCaller().call(new Base());
      new DerivedCaller().call(new Derived());
   }
}