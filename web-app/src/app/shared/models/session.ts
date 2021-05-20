/**
 * Session object, stores unique id from back end. All additional information
 * from user is chained with user.
 */
export class Session {
  sessionID: number;
  sessionUsername: string;
  sessionLoggedIn: boolean;
  commonUser: boolean;
  bandID?: number;

  constructor() {
    this.sessionLoggedIn = false;
  }
}
