## Vivado project

### Files
- RTL code (./fpga/src/rtl): Implement the digital circuit on the FPGA.
  - .v (Verilog)
  - .vhd (VHDL) 
  - A module in Verilog or VHDL will be set as the Top Module, served as the "main function".
- Constraints (./fpga/src/constraints): Specifying the hardware pins.
  - .xdc
  - Specify the hardware pin and voltage standard of a pin in the top module.
  - Typically provided by the manufacturer. (for example, https://www.tulembedded.com/fpga/ProductsPYNQ-Z2.html)
  - The sysclk pin in the top module is connected to the H16 pin of the real FPGA chip. The input voltage will be 3.3 V.
  ```
  set_property -dict { PACKAGE_PIN H16   IOSTANDARD LVCMOS33 } [get_ports { sysclk }];
  ```
  - Specify the frequency of the sysclk pin. Only required for clock pins to check the timing performance.
  ```
  create_clock -add -name sys_clk_pin -period 8.00 -waveform {0 4} [get_ports { sysclk }];
  ```
- Scripts (./fpga/script/): Every operation we do in the GUI can be accomplished by a TCL command.
  - .tcl
  - Resource
    - https://github.com/byu-cpe/BYU-Computing-Tutorials/wiki/TclVivado
    - https://wiki.tcl-lang.org/page/Tcl+Tutorial+Lesson+0

### Block Design
- A GUI design tool for configurating IPs provivdes by Vivado and connecting IPs with existing codes.
- 