file copy -force ${BUILD_PREFIX}/${TOP_MODULE}.runs/impl_1/${BD_NAME}_wrapper.bit ${BUILD_PREFIX}/${TOP_MODULE}.bit
file copy -force ${BUILD_PREFIX}/bd/${BD_NAME}/hw_handoff/${BD_NAME}.hwh ${BUILD_PREFIX}/${TOP_MODULE}.hwh
exec tar -czf ./${TOP_MODULE}.tgz -C ${BUILD_PREFIX} ${TOP_MODULE}.hwh ${TOP_MODULE}.bit