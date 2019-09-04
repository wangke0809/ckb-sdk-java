package type;

import java.io.IOException;
import java.util.Collections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nervos.ckb.crypto.Hash;
import org.nervos.ckb.methods.type.Script;
import org.nervos.ckb.service.CKBService;
import org.nervos.ckb.service.HttpService;

/** Copyright © 2018 Nervos Foundation. All rights reserved. */
public class ScriptTest {

  @Test
  public void testScriptHashWithCodeHash() throws IOException {
    CKBService ckbService = CKBService.build(new HttpService("http://18.162.80.155:8114"));
    String codeHash =
        Hash.blake2b(
            "0x1400000000000e00100000000c000800000004000e0000000c00000014000000740100000000000000000600080004000600000004000000580100007f454c460201010000000000000000000200f3000100000078000100000000004000000000000000980000000000000005000000400038000100400003000200010000000500000000000000000000000000010000000000000001000000000082000000000000008200000000000000001000000000000001459308d00573000000002e7368737472746162002e74657874000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000b000000010000000600000000000000780001000000000078000000000000000a000000000000000000000000000000020000000000000000000000000000000100000003000000000000000000000000000000000000008200000000000000110000000000000000000000000000000100000000000000000000000000000000000000");
    Script script = new Script(codeHash, Collections.emptyList(), Script.DATA);
    Assertions.assertEquals(
        ckbService.computeScriptHash(script).send().getScriptHash(), script.scriptHash());
  }
}
