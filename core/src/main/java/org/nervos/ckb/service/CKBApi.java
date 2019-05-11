package org.nervos.ckb.service;

import org.nervos.ckb.methods.Request;
import org.nervos.ckb.methods.response.*;
import org.nervos.ckb.methods.type.OutPoint;
import org.nervos.ckb.methods.type.Transaction;

/** Created by duanyytop on 2018-12-20. Copyright © 2018 Nervos Foundation. All rights reserved. */
public interface CKBApi {

  Request<?, CkbBlock> getBlock(String blockHash);

  Request<?, CkbBlock> getBlockByNumber(String blockNumber);

  Request<?, CkbTransaction> getTransaction(String transactionHash);

  Request<?, CkbBlockHash> getBlockHash(String blockNumber);

  Request<?, CkbHeader> getTipHeader();

  Request<?, CkbCells> getCellsByLockHash(
      String lockHash, String fromBlockNumber, String toBlockNumber);

  Request<?, CkbCell> getLiveCell(OutPoint outPoint);

  Request<?, CkbBlockNumber> getTipBlockNumber();

  Request<?, CkbEpoch> getCurrentEpoch();

  Request<?, CkbEpoch> getEpochByNumber(String epochNumber);

  Request<?, CkbNodeInfo> localNodeInfo();

  Request<?, CkbPeers> getPeers();

  Request<?, CkbBlockchainInfo> getBlockchainInfo();

  Request<?, CkbPeersState> getPeersState();

  Request<?, CkbTransactionHash> sendTransaction(Transaction transaction);

  Request<?, CkbCycles> dryRunTransaction(Transaction transaction);

  Request<?, CkbTxPoolInfo> txPoolInfo();
}
