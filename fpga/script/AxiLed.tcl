set TOP_MODULE AxiLed

set PROJ_NAME ${TOP_MODULE}
set BUILD_PREFIX ./${PROJ_NAME}
set SOURCE_PATH ../src
set SCRIPT_PATH ../script

set BD_NAME bd

# create project
create_project ${PROJ_NAME} ${BUILD_PREFIX} -part xc7z020clg400-1 -force

# add source files
add_files ${SOURCE_PATH}/rtl/${TOP_MODULE}.v

add_files -fileset constrs_1 ${SOURCE_PATH}/constraints/PYNQ-Z2.xdc

# create block design
create_bd_design -dir ${BUILD_PREFIX}/bd ${BD_NAME}

# add zynq ps to bd
source ${SCRIPT_PATH}/utils/ZynqPs.tcl

# create top module in bd
create_bd_cell -type module -reference ${TOP_MODULE} Top

# add ports to bd and connect to top module
source ${SCRIPT_PATH}/utils/PYNQZ2Ports.tcl

# add axi smart connect to bd
source ${SCRIPT_PATH}/utils/AxiSmartConnect.tcl

# block design ending routines
validate_bd_design
make_wrapper -files [get_files ${BD_NAME}.bd] -top -import -force
close_bd_design ${BD_NAME}
generate_target all [get_files ${BD_NAME}.bd]
set_property -name "top" -value "${BD_NAME}_wrapper" -objects [get_filesets sources_1]

# run synthesis
launch_runs synth_1
wait_on_run synth_1

# run implementation
launch_runs impl_1 -to_step write_bitstream
wait_on_run impl_1

source ${SCRIPT_PATH}/utils/PackBitstream.tcl