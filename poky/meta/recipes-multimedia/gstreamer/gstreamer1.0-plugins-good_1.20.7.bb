require gstreamer1.0-plugins-common.inc

DESCRIPTION = "'Good' GStreamer plugins"
HOMEPAGE = "https://gstreamer.freedesktop.org/"
BUGTRACKER = "https://gitlab.freedesktop.org/gstreamer/gst-plugins-good/-/issues"

SRC_URI = "https://gstreamer.freedesktop.org/src/gst-plugins-good/gst-plugins-good-${PV}.tar.xz \
           file://0001-qt-include-ext-qt-gstqtgl.h-instead-of-gst-gl-gstglf.patch \
           file://CVE-2024-47537_47539_47543_47544_47545_47546_47596_47597_47598-pre1.patch \
           file://CVE-2024-47537_47539_47543_47544_47545_47546_47596_47597_47598-1.patch \
           file://CVE-2024-47537_47539_47543_47544_47545_47546_47596_47597_47598-2.patch \
           file://CVE-2024-47537_47539_47543_47544_47545_47546_47596_47597_47598-3.patch \
           file://CVE-2024-47537_47539_47543_47544_47545_47546_47596_47597_47598-4.patch \
           file://CVE-2024-47537_47539_47543_47544_47545_47546_47596_47597_47598-5.patch \
           file://CVE-2024-47537_47539_47543_47544_47545_47546_47596_47597_47598-6.patch \
           file://CVE-2024-47537_47539_47543_47544_47545_47546_47596_47597_47598-7.patch \
           file://CVE-2024-47537_47539_47543_47544_47545_47546_47596_47597_47598-8.patch \
           file://CVE-2024-47537_47539_47543_47544_47545_47546_47596_47597_47598-9.patch \
           file://CVE-2024-47537_47539_47543_47544_47545_47546_47596_47597_47598-10.patch \
           file://CVE-2024-47537_47539_47543_47544_47545_47546_47596_47597_47598-11.patch \
           file://CVE-2024-47537_47539_47543_47544_47545_47546_47596_47597_47598-12.patch \
           file://CVE-2024-47599.patch \
           file://CVE-2024-47540_47601_47602_47603_47834-1.patch \
           file://CVE-2024-47540_47601_47602_47603_47834-2.patch \
           file://CVE-2024-47540_47601_47602_47603_47834-3.patch \
           file://CVE-2024-47540_47601_47602_47603_47834-4.patch \
           file://CVE-2024-47540_47601_47602_47603_47834-5.patch \
           file://CVE-2024-47540_47601_47602_47603_47834-6.patch \
           file://CVE-2024-47540_47601_47602_47603_47834-7.patch \
           file://CVE-2024-47606.patch \
           file://CVE-2024-47613.patch \
           file://CVE-2024-47774.patch \
           file://CVE-2024-47775_47776_47777_47778-1.patch \
           file://CVE-2024-47775_47776_47777_47778-2.patch \
           file://CVE-2024-47775_47776_47777_47778-3.patch \
           file://CVE-2024-47775_47776_47777_47778-4.patch \
           file://CVE-2024-47775_47776_47777_47778-5.patch \
           file://CVE-2024-47775_47776_47777_47778-6.patch \
           file://CVE-2024-47775_47776_47777_47778-7.patch \
           "

SRC_URI[sha256sum] = "599f093cc833a1e346939ab6e78a3f8046855b6da13520aae80dd385434f4ab2"

S = "${WORKDIR}/gst-plugins-good-${PV}"

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://gst/replaygain/rganalysis.c;beginline=1;endline=23;md5=b60ebefd5b2f5a8e0cab6bfee391a5fe"

DEPENDS += "gstreamer1.0-plugins-base libcap zlib"
RPROVIDES:${PN}-pulseaudio += "${PN}-pulse"
RPROVIDES:${PN}-soup += "${PN}-souphttpsrc"

PACKAGECONFIG ??= " \
    ${GSTREAMER_ORC} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'pulseaudio x11', d)} \
    ${@bb.utils.contains('TUNE_FEATURES', 'm64', 'asm', '', d)} \
    bz2 cairo flac gdk-pixbuf gudev jpeg lame libpng mpg123 soup speex taglib v4l2 \
"

X11DEPENDS = "virtual/libx11 libsm libxrender libxfixes libxdamage"
X11ENABLEOPTS = "-Dximagesrc=enabled -Dximagesrc-xshm=enabled -Dximagesrc-xfixes=enabled -Dximagesrc-xdamage=enabled"
X11DISABLEOPTS = "-Dximagesrc=disabled -Dximagesrc-xshm=disabled -Dximagesrc-xfixes=disabled -Dximagesrc-xdamage=disabled"

QT5WAYLANDDEPENDS = "${@bb.utils.contains("DISTRO_FEATURES", "wayland", "qtwayland", "", d)}"

PACKAGECONFIG[asm]        = "-Dasm=enabled,-Dasm=disabled,nasm-native"
PACKAGECONFIG[bz2]        = "-Dbz2=enabled,-Dbz2=disabled,bzip2"
PACKAGECONFIG[cairo]      = "-Dcairo=enabled,-Dcairo=disabled,cairo"
PACKAGECONFIG[dv1394]     = "-Ddv1394=enabled,-Ddv1394=disabled,libiec61883 libavc1394 libraw1394"
PACKAGECONFIG[flac]       = "-Dflac=enabled,-Dflac=disabled,flac"
PACKAGECONFIG[gdk-pixbuf] = "-Dgdk-pixbuf=enabled,-Dgdk-pixbuf=disabled,gdk-pixbuf"
PACKAGECONFIG[gtk]        = "-Dgtk3=enabled,-Dgtk3=disabled,gtk+3"
PACKAGECONFIG[gudev]      = "-Dv4l2-gudev=enabled,-Dv4l2-gudev=disabled,libgudev"
PACKAGECONFIG[jack]       = "-Djack=enabled,-Djack=disabled,jack"
PACKAGECONFIG[jpeg]       = "-Djpeg=enabled,-Djpeg=disabled,jpeg"
PACKAGECONFIG[lame]       = "-Dlame=enabled,-Dlame=disabled,lame"
PACKAGECONFIG[libpng]     = "-Dpng=enabled,-Dpng=disabled,libpng"
PACKAGECONFIG[libv4l2]    = "-Dv4l2-libv4l2=enabled,-Dv4l2-libv4l2=disabled,v4l-utils"
PACKAGECONFIG[mpg123]     = "-Dmpg123=enabled,-Dmpg123=disabled,mpg123"
PACKAGECONFIG[pulseaudio] = "-Dpulse=enabled,-Dpulse=disabled,pulseaudio"
PACKAGECONFIG[qt5]        = "-Dqt5=enabled,-Dqt5=disabled,qtbase qtdeclarative qtbase-native ${QT5WAYLANDDEPENDS}"
# Starting with version 1.20, the GStreamer soup plugin loads libsoup with dlopen()
# instead of linking to it. And instead of using the default libsoup C headers, it
# uses its own stub header. Consequently, objdump will not show the libsoup .so as
# a dependency, and libsoup won't be added to an image. Fix this by setting libsoup
# as RDEPEND instead of DEPEND.
PACKAGECONFIG[soup]       = "-Dsoup=enabled,-Dsoup=disabled,,libsoup-2.4"
PACKAGECONFIG[speex]      = "-Dspeex=enabled,-Dspeex=disabled,speex"
PACKAGECONFIG[rpi]        = "-Drpicamsrc=enabled,-Drpicamsrc=disabled,userland"
PACKAGECONFIG[taglib]     = "-Dtaglib=enabled,-Dtaglib=disabled,taglib"
PACKAGECONFIG[v4l2]       = "-Dv4l2=enabled -Dv4l2-probe=true,-Dv4l2=disabled -Dv4l2-probe=false"
PACKAGECONFIG[vpx]        = "-Dvpx=enabled,-Dvpx=disabled,libvpx"
PACKAGECONFIG[wavpack]    = "-Dwavpack=enabled,-Dwavpack=disabled,wavpack"
PACKAGECONFIG[x11]        = "${X11ENABLEOPTS},${X11DISABLEOPTS},${X11DEPENDS}"

EXTRA_OEMESON += " \
    -Ddoc=disabled \
    -Daalib=disabled \
    -Ddirectsound=disabled \
    -Ddv=disabled \
    -Dlibcaca=disabled \
    -Doss=enabled \
    -Doss4=disabled \
    -Dosxaudio=disabled \
    -Dosxvideo=disabled \
    -Dshout2=disabled \
    -Dtwolame=disabled \
    -Dwaveform=disabled \
"

FILES:${PN}-equalizer += "${datadir}/gstreamer-1.0/presets/*.prs"
