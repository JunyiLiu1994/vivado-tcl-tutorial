create_bd_cell -type ip -vlnv xilinx.com:ip:smartconnect:1.0 AxiSmartConnect
set_property -dict [list \
  CONFIG.HAS_ARESETN {0} \
  CONFIG.NUM_CLKS {2} \
  CONFIG.NUM_SI {1} \
] [get_bd_cells AxiSmartConnect]
connect_bd_net [get_bd_pins ZynqPs/FCLK_CLK0] [get_bd_pins AxiSmartConnect/aclk]
connect_bd_net [get_bd_ports sysclk] [get_bd_pins AxiSmartConnect/aclk1]
connect_bd_intf_net [get_bd_intf_pins ZynqPs/M_AXI_GP0] [get_bd_intf_pins AxiSmartConnect/S00_AXI]
connect_bd_intf_net [get_bd_intf_pins AxiSmartConnect/M00_AXI] [get_bd_intf_pins Top/AXI]

assign_bd_address -offset 0x40000000 -range 0x40000000 -target_address_space [get_bd_addr_spaces ZynqPs/Data] [get_bd_addr_segs Top/AXI/reg0] -force