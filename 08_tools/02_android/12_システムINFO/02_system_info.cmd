adb get-serialno

echo MAC
adb shell  cat /sys/class/net/wlan0/address

adb shell wm size

adb shell wm density


adb shell getprop ro.build.version.sdk

adb shell getprop ro.build.version.release

adb shell getprop ro.build.version.security_patch

adb shell getprop ro.product.model

adb shell getprop ro.product.brand

adb shell getprop ro.product.name

adb shell getprop ro.product.board

adb shell getprop ro.product.cpu.abilist

adb shell getprop persist.sys.isUsbOtgEnabled

adb shell getprop dalvik.vm.heapsize

adb shell getprop ro.sf.lcd_density

