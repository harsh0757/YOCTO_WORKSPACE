KBRANCH ?= "v5.10/standard/tiny/base"
KBRANCH:qemuarm  ?= "v5.10/standard/tiny/arm-versatile-926ejs"

LINUX_KERNEL_TYPE = "tiny"
KCONFIG_MODE = "--allnoconfig"

require recipes-kernel/linux/linux-yocto.inc

LINUX_VERSION ?= "5.10.227"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "${@bb.utils.contains('ARCH', 'x86', 'elfutils-native', '', d)}"
DEPENDS += "openssl-native util-linux-native"

KMETA = "kernel-meta"
KCONF_BSP_AUDIT_LEVEL = "2"

SRCREV_machine:qemuarm ?= "5bb97dac8754ff70fb2ccdef76702bc9a96ce3f7"
SRCREV_machine ?= "42d448fa7ff4633abcbf0a7a02ce0b2509df44a9"
SRCREV_meta ?= "f5f141c395405345203f11703ca4bf609fef3fc2"

PV = "${LINUX_VERSION}+git${SRCPV}"

SRC_URI = "git://git.yoctoproject.org/linux-yocto.git;branch=${KBRANCH};name=machine \
           git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=yocto-5.10;destsuffix=${KMETA}"

COMPATIBLE_MACHINE = "^(qemux86|qemux86-64|qemuarm|qemuarmv5)$"

# Functionality flags
KERNEL_FEATURES = ""

KERNEL_DEVICETREE:qemuarmv5 = "versatile-pb.dtb"
