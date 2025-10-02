package vivado.tutorial

import spinal.core._
import spinal.lib._
import spinal.lib.bus.amba4.axi.Axi4Config
import spinal.lib.bus.amba4.axi.Axi4

case class PynqZ2Io() extends Bundle with IMasterSlave {
  val sw = Bits(2 bits)
  val btn = Bits(4 bits)
  val led = Bits(4 bits)
  val led4_b = Bool()
  val led4_g = Bool()
  val led4_r = Bool()
  val led5_b = Bool()
  val led5_g = Bool()
  val led5_r = Bool()

  val axi = Axi4(
    Axi4Config(
      addressWidth = 32,
      dataWidth = 32,
      idWidth = 2
    )
  )

  override def asMaster(): Unit = {
    in(led, led4_b, led4_g, led4_r, led5_b, led5_g, led5_r)
    out(sw, btn)
    master(axi)
  }

  def rgbLedOff(): Unit = {
    led4_b := False
    led4_g := False
    led4_r := False
    led5_b := False
    led5_g := False
    led5_r := False
  }

  def ledOff(): Unit = {
    led := 0
  }
}

object VivadoAxiInference {
  def apply(axi: Axi4, ifcName: String = "AXI"): Unit = {
    axi.aw.valid.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " AWVALID"
    )
    axi.aw.ready.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " AWREADY"
    )
    axi.aw.addr.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " AWADDR"
    )
    Option(axi.aw.id).foreach(
      _.addAttribute(
        "X_INTERFACE_INFO",
        "xilinx.com:interface:aximm:1.0 " + ifcName + " AWID"
      )
    )
    axi.aw.region.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " AWREGION"
    )
    axi.aw.len.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " AWLEN"
    )
    axi.aw.size.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " AWSIZE"
    )
    axi.aw.burst.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " AWBURST"
    )
    axi.aw.lock.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " AWLOCK"
    )
    axi.aw.cache.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " AWCACHE"
    )
    axi.aw.qos.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " AWQOS"
    )
    axi.aw.prot.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " AWPROT"
    )
    // axi.aw.user.addAttribute("X_INTERFACE_INFO", "xilinx.com:interface:aximm:1.0 " + ifcName +" AWUSER")
    axi.w.valid.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " WVALID"
    )
    axi.w.ready.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " WREADY"
    )
    axi.w.data.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " WDATA"
    )
    axi.w.strb.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " WSTRB"
    )
    axi.w.last.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " WLAST"
    )
    Option(axi.w.id).foreach(
      _.addAttribute(
        "X_INTERFACE_INFO",
        "xilinx.com:interface:aximm:1.0 " + ifcName + " WID"
      )
    )
    // axi.w.user.addAttribute("X_INTERFACE_INFO", "xilinx.com:interface:aximm:1.0 " + ifcName +" WUSER")
    axi.b.valid.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " BVALID"
    )
    axi.b.ready.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " BREADY"
    )
    Option(axi.b.id).foreach(
      _.addAttribute(
        "X_INTERFACE_INFO",
        "xilinx.com:interface:aximm:1.0 " + ifcName + " BID"
      )
    )
    axi.b.resp.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " BRESP"
    )
    // axi.b.user.addAttribute("X_INTERFACE_INFO", "xilinx.com:interface:aximm:1.0 " + ifcName +" BUSER")
    axi.ar.valid.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " ARVALID"
    )
    axi.ar.ready.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " ARREADY"
    )
    axi.ar.addr.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " ARADDR"
    )
    Option(axi.ar.id).foreach(
      _.addAttribute(
        "X_INTERFACE_INFO",
        "xilinx.com:interface:aximm:1.0 " + ifcName + " ARID"
      )
    )
    axi.ar.region.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " ARREGION"
    )
    axi.ar.len.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " ARLEN"
    )
    axi.ar.size.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " ARSIZE"
    )
    axi.ar.burst.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " ARBURST"
    )
    axi.ar.lock.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " ARLOCK"
    )
    axi.ar.cache.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " ARCACHE"
    )
    axi.ar.qos.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " ARQOS"
    )
    axi.ar.prot.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " ARPROT"
    )
    // axi.ar.user.addAttribute("X_INTERFACE_INFO", "xilinx.com:interface:aximm:1.0 " + ifcName +" ARUSER")
    axi.r.valid.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " RVALID"
    )
    axi.r.ready.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " RREADY"
    )
    axi.r.data.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " RDATA"
    )
    Option(axi.r.id).foreach(
      _.addAttribute(
        "X_INTERFACE_INFO",
        "xilinx.com:interface:aximm:1.0 " + ifcName + " RID"
      )
    )
    axi.r.resp.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " RRESP"
    )
    axi.r.last.addAttribute(
      "X_INTERFACE_INFO",
      "xilinx.com:interface:aximm:1.0 " + ifcName + " RLAST"
    )
    // axi.r.user.addAttribute("X_INTERFACE_INFO", "xilinx.com:interface:aximm:1.0 " + ifcName +" RUSER")
  }
}
