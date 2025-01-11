inherit autotools

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://example1.c"

S = "${WORKDIR}"

do_compile() {
    ${CC} ${CFLAGS} ${LDFLAGS} ${WORKDIR}/example1.c -o ${WORKDIR}/example1
}

do_install() {
    install -d ${D}/${bindir}
    install -m 0755 ${S}/example1 ${D}/${bindir}
}
